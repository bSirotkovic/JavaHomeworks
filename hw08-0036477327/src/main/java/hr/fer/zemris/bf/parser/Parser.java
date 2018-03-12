package hr.fer.zemris.bf.parser;

import hr.fer.zemris.bf.lexer.Lexer;
import hr.fer.zemris.bf.lexer.LexerException;
import hr.fer.zemris.bf.lexer.Token;
import hr.fer.zemris.bf.lexer.TokenType;
import hr.fer.zemris.bf.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Parser {

	private Node expression;
	private Lexer lexer;

	public Parser(String expression) {
		lexer = new Lexer(expression);
		try {
			this.expression = S();
		} catch (ParserException ex) {
			throw ex;
		} catch (LexerException ex) {
			throw new ParserException(ex.getMessage());
		}
	}

	public Node getExpression() {
		return expression;
	}

	private Node S() {
		return E1();
	}

	private Node E1() {
		return EMulti("or", (b1, b2) -> b1 | b2, this::E2);
	}

	private Node E2() {
		return EMulti("xor", (b1, b2) -> b1 ^ b2, this::E3);
	}

	private Node E3() {
		return EMulti("and", (b1, b2) -> b1 & b2, this::E4);
	}

	private Node EMulti(String name, BinaryOperator<Boolean> operator, Supplier<Node> callback) {
		List<Node> children = new ArrayList<>();
		children.add(callback.get());

		Token t = lexer.peekToken();
		while (t.getTokenType() == TokenType.OPERATOR && t.getTokenValue().equals(name)) {
			lexer.nextToken(); // consume that token
			children.add(callback.get());
			t = lexer.peekToken();
		}

		if (children.size() == 1) {
			return children.get(0);
		}

		return new BinaryOperatorNode(name, children, operator);
	}

	private Node E4() {
		Token token = lexer.peekToken();
		if (token != null && token.getTokenType() == TokenType.OPERATOR && token.getTokenValue().equals("not")) {
			lexer.nextToken(); // consume not
			Node node = E4();
			return new UnaryOperatorNode((String) token.getTokenValue(), node, v -> !v);
		} else {
			return E5();
		}
	}

	private Node E5() {
		Token token = lexer.nextToken();
		switch (token.getTokenType()) {
			case VARIABLE:
				return new VariableNode(token.getTokenValue().toString());
			case CONSTANT:
				try {
					return new ConstantNode((boolean) token.getTokenValue());
				} catch (LexerException ex) {
					throw new ParserException("Constant boolean value expected");
				}
			case OPEN_BRACKET:
				Node node = E1();
				lexer.nextToken();
				return node;
			default:
				throw new ParserException("Invalid token type in E5");
		}
	}

}