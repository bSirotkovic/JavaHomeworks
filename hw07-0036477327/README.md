# 7. homework assignment; JAVA, Academic year 2016/2017; FER

## Napravite prazan Maven projekt, kao u 1. zadaci: u Eclipsovom workspace direktoriju napravite direktorij

## hw07-0000000000 (zamijenite nule Vašim JMBAG-om) te u njemu oformite Mavenov projekt

## hr.fer.zemris.java.jmbag0000000000:hw07-0000000000 (zamijenite nule Vašim JMBAG-om) i

## dodajte ovisnost prema junit:junit:4.12. Importajte projekt u Eclipse. Sada možete nastaviti s

## rješavanjem zadataka.

## Skrecem pažnju: rješenje ove zadace koristit cete kao osnovu za sljedecu domacu zadacu.

## Razvijamo sustav koji ce nam omoguciti rad s Booleovim funkcijama. U okviru ove domace zadace

## napravit cete podršku za nekoliko osnovnih operacija.

## Pa krenimo redom.

## Problem 1.

## Napravite paket hr.fer.zemris.bf.lexer. U njega smjestite razrede Lexer, LexerException (izveden iz

## RuntimeException) i Token te enum TokenType (EOF, VARIABLE, CONSTANT, OPERATOR, OPEN_BRACKET,

## CLOSED_BRACKET). Razred Lexer nudi sljedece javno sucelje (pojam "javno sucelje" ovdje ne predstavlja

## doslovno sucelje u smislu kljucne rijeci interface, vec popis svega javnoga što je vidljivo – u nastavku je

## to jedan javni konstruktor i jedna javna clanska metoda):

**public** Lexer(String expression);
**public** Token nextToken();

## Razred Token nudi sljedece javno sucelje:

**public** Token(TokenType tokenType, Object tokenValue);
**public** TokenType getTokenType();
**public** Object getTokenValue();
**public** String toString();

## Evo primjera:

**try** {
Lexer lexer = **new** Lexer("(false or b) xor not (a or c)");
Token token = **null** ;
**do** {
token = lexer.nextToken();
System. **_out_** .println(token);
} **while** (token.getTokenType()!=TokenType. **_EOF_** );
} **catch** (LexerException ex) {
System. **_out_** .println("Iznimka: " + ex.getClass()+" - " + ex.getMessage());
}

## Lexer obraduje ulaz koji predstavlja Booleov izraz. Booleov izraz se sastoji od konstanti, varijabli,

## operatora te otvorenih i zatvorenih zagrada. Prilikom izrade lexera, pridržavajte se sljedecih pravila.

## Identifikator je svaki slijed znakova koji pocinje slovom (Character#isLetter) nakon cega slijedi nula ili

## više slova ili brojeva (Character#isLetterOrDigit) ili znakova podvlake.


### Numericki niz je svaki niz koji pocinje znamenkom i nakon toga slijedi nula ili više znamenaka.

### Tokeni koje lexer generira razrješavaju se prema prioritetima (od vrha prema dnu) kako je napisano u

### nastavku.

### Praznine (razmaknice, tabovi, enteri) se preskacu.

### Ako je lexer pronašao identifikator :

### 1. ako je to "and", "xor", "or", "not" ili bilo koja kombinacija velikih i malih slova tih rijeci, lexer

### vraca token tipa OPERATOR cija je vrijednost string naziva operatora (uvijek malim slovima);

### 2. ako je to "true" ili "false" ili bilo koja kombinacija velikih i malih slova tih rijeci, lexer vraca

### token tipa CONSTANT cija je vrijednost odgovarajuci Boolean objekt;

### 3. inace se vraca token tipa VARIABLE cija je vrijednost String cija su sva slova velika.

### Ako je lexer pronašao numericki niz :

### 1. ako je to 0 ili 1 , lexer vraca token tipa CONSTANT cija je vrijednost odgovarajuci Boolean objekt

### (true za 1 , false za 0 );

### 2. inace se baca iznimka.

### Ako je lexer pronašao '(' ili ')', vraca se token tipa OPEN_BRACKET odnosno CLOSED_BRACKET cija je

### vrijednost Character objekt koji predstavlja pronadeni simbol.

### Ako je lexer pronašao '*', '+', '!', ':+:', vraca se token tipa OPERATOR cija je vrijednost string naziva

### operatora malim slovima (redom: "and", "or", "not", "xor").

### Ako ništa od prethodnoga ne pokriva pronadeno, lexer baca iznimku.

### U okviru ovog zadatka pripremio sam Vam i nekoliko demonstracijskih programa. ZIP arhiva uploadana je

### kao privitak. Priložene programe trebate ubaciti u paket demo redosljedom kojim ih ovdje navedemo.

### Nemojte ih u projekt dodavati prije toga jer cete imati niz pogrešaka zbog referenciranja nepostojecih

### razreda odnosno metoda. Za svaki program datoteka istog imena ali ekstenzije txt sadrži ispis programa

### kakav trebate dobiti kada pokrenete taj program.

### Ubacite u projekt demonstracijski program Izrazi1.java. Napišite lexer i zadane razrede, te osigurajte da

### Vam ispitni program daje izlaz kakav je prikazan u Izrazi1.txt.

## Problem 2.

### Napravite paket hr.fer.zemris.bf.parser. U njega smjestite razrede Parser, i ParserException

### (izveden iz RuntimeException). Javno sucelje razreda Parser treba biti:

**public** Parser(String expression);
**public** Node getExpression();

### Parser izvedite kao parser rekurzivnog spusta, cija je gramatika (S je pocetni nezavršni simbol):

#### S -> E

#### E1 -> E2 ( OR E2)*

#### E2 -> E3 ( XOR E3)*

#### E3 -> E4 ( AND E4)*

#### E4 -> NOT E4 | E

#### E5 -> VAR | KONST | '(' E1 ')'


### U prethodnoj gramatici, crvenim su oznaceni tokeni. Pravila za E1, E2 i E3 napisana su na nacin da

### dozvoljavaju da se operator dan u pravilu pojavi uzastopno nula, jednom ili više puta. Primjerice, naleti li

### parser negdje na niz a or b or c or d, to treba "shvatiti" kao primjenu operatora OR nad cetiri djeteta:

### listom (a, b, c, d).

### Zadaca parsera je izgradnja stabla koje predstavlja parsirani izraz.

### Napravite paket hr.fer.zemris.bf.model. U njega cemo smjestiti sucelja i razrede koji modeliraju ovo

### stablo i operacije nad njime. Svaki cvor stabla modeliran je suceljem Node:

**public interface** Node {
**void** accept(NodeVisitor visitor);
}

### Konstante su modelirane cvorom tipa ConstantNode. Konstruktor prima vrijednost konstante i pamti je.

**public** ConstantNode( **boolean** value) {...}
**public void** accept(NodeVisitor visitor) {...}
**public boolean** getValue() {...}

### Varijable su modelirane cvorom tipa VariableNode. Konstruktor prima ime varijable i pamti je.

**public** VariableNode(String name) {...}
**public void** accept(NodeVisitor visitor) {...}
**public** String getName() {...}

### Unarne operacije (poput komplementiranja) modelirane su cvorom tipa UnaryOperatorNode. Konstruktor

### prima naziv operatora, referencu na cvor koji predstavlja operand nad kojim treba djelovati te strategiju koja

### implementira djelovanje samog operatora.

**public** UnaryOperatorNode(String name, Node child, UnaryOperator<Boolean> operator)
{...}
**public void** accept(NodeVisitor visitor) {...}
**public** String getName() {...}
**public** Node getChild() {...}
**public** UnaryOperator<Boolean> getOperator() {...}

### Binarne operacije (poput i, ili, ...) modelirane su cvorom tipa BinaryOperatorNode. Konstruktor prima

### naziv operatora, listu referenci na proizvoljan broj cvorova koji predstavljaju operande nad kojim treba

### djelovati (minimalno ih mora biti 2 ali ih može biti i više) te strategiju koja implementira djelovanje samog

### binarnog operatora.

**public** BinaryOperatorNode(String name, List<Node> children, BinaryOperator<Boolean>
operator) {...}
**public void** accept(NodeVisitor visitor) {...}
**public** String getName() {...}
**public** List<Node> getChildren() {...}
**public** BinaryOperator<Boolean> getOperator() {...}

### Operacije koje cemo izvoditi nad stablom riješit cemo primjenom oblikovnog obrasca Posjetitelj (engl.

### Visitor ). Primjer ovog oblikovnog obrasca opisan je u knjizi u poglavlju Studija slucaja: jezik Vlang , a i

### Internet obiluje informacijama. Ako niste sigurni jeste li dobro shvatili ideju ovog obrasca, slobodno me

### potražite pa pitajte. Ovaj oblikovni obrazac koristit cete u još nekim domacim zadacama.


### Posjetitelji su kod nas modelirani suceljem NodeVisitor.

```
void visit(ConstantNode node);
void visit(VariableNode node);
void visit(UnaryOperatorNode node);
void visit(BinaryOperatorNode node);
```
### Svaki cvor stoga navodi metodu accept koja prima referencu nad posjetiteljem, i nad njim poziva metodu

### koja odgovara upravo tipu samog cvora.

### Napišite prethodno definirane razrede i sucelja modela, i potom napišite parser.

### Najjednostavniji nacin da provjerite jeste li dobro implementirali parser jest da pokušate ispisati stablo koje

### je nastalo. U tu svrhu napisat cete Vašeg prvog posjetitelja: razred ExpressionTreePrinter. Sve

### posjetitelje smjestit cemo u paket hr.fer.zemris.bf.utils. Napravite taj paket i u njemu razred

### ExpressionTreePrinter koji implementira sucelje NodeVisitor. Zadaca ovog posjetitelja jest da na

### zaslon ispisuje nastalo stablo uz vodenje racuna o indentaciji (svaki puta kada ude u neki operator,

### indentaciju treba povecati za 2). Evo primjera uporabe i generiranog ispisa.

Parser parser = **new** Parser("(d or b) xor not (a or c)");
parser.getExpression().accept( **new** ExpressionTreePrinter());

### Ispis ce biti:

xor
or
D
B
not
or
A
C

### iz cega je vidljivo da xor ima dva djeteta: cvor or i cvor not. Cvor or ima dva djeteta: varijable D i B. Cvor

### not ima jedno dijete: operator or koji ima dva djeteta: varijable A i C.

### Ubacite u projekt demonstracijski program Izrazi2.java. Osigurajte da Vam ispitni program daje izlaz

### kakav je prikazan u Izrazi2.txt.

## Problem 3.

### Napišite posjetitelja VariablesGetter koji ce odrediti sve varijable koje se spominju u izrazu i koje ce

### vratiti kao listu naziva varijabli sortiranu leksikografski. Naravno, elementi liste moraju biti jedinstveni. Evo

### primjera uporabe:

Parser parser = **new** Parser("(c + a) xor (a or b)");
VariablesGetter getter = **new** VariablesGetter();
parser.getExpression().accept(getter);
List<String> variables = getter.getVariables();

### Rezultat ce biti lista ("A", "B", "C").

### Ubacite u projekt demonstracijski program Izrazi3.java. Osigurajte da Vam ispitni program daje izlaz

### kakav je prikazan u Izrazi3.txt.


## Problem 4.

### U paket hr.fer.zemris.bf.utils dodajte razred Util. Dodajte u njega staticku metodu:

**public static void** forEach(List<String> variables, Consumer< **boolean** []> consumer);

### Zadaca ove metode jest da za zadanu listu varijabli generira redom sve kombinacije vrijednosti (kao da

### želite generirati tablicu istinitosti), te za svaku kombinaciju vrijednosti poziva definirani consumer.

### Vrijednosti se moraju generirati upravo redosljedom kojim biste ih slagali u tablici istinitosti: od svih nula

### (tj. false) prema svim jedinicama (tj. true), gdje je nabrže mijenja najdesnija varijabla (zadnji element liste).

### Evo primjera:

Util. _forEach_ (
Arrays. _asList_ ("A","B","C"),
values ->
System. **_out_** .println(
Arrays. _toString_ (values)
.replaceAll("true", "1")
.replaceAll("false", "0")
)
);

### Rezultat ce biti:

#### [0, 0, 0]

#### [0, 0, 1]

#### [0, 1, 0]

#### [0, 1, 1]

#### [1, 0, 0]

#### [1, 0, 1]

#### [1, 1, 0]

#### [1, 1, 1]

### Ovaj primjer imate kao demonstracijski program ForEachDemo1.java; ubacite ga u projekt i osigurajte da

### Vam daje prikazani izlaz.

## Problem 5.

### U paket hr.fer.zemris.bf.utils dodajte novog posjetitelja, razred ExpressionEvaluator. Zadaca

### ovog posjetitelja jest izracun vrijednosti izraza za zadanu kombinaciju ulaznih varijabli. Pogledajmo najprije

### primjer uporabe.

Node expression = **new** Parser("A and b or C").getExpression();
List<String> variables = Arrays. _asList_ ("A", "B", "C");
ExpressionEvaluator eval = **new** ExpressionEvaluator(variables);

eval.setValues( **new boolean** [] { **false** , **false** , **false** });
expression.accept(eval);
System. **_out_** .println("f(A,B,C) = f(0,0,0) = " + eval.getResult());

eval.setValues( **new boolean** [] { **false** , **false** , **true** });
expression.accept(eval);
System. **_out_** .println("f(A,B,C) = f(0,0,1) = " + eval.getResult());

### Što se dogada u prethodnom primjeru? Stvorili smo posjetitelja i predali mu popis varijabli A, B, C. Potom


### smo pozvali setValues i predali polje od tri booleove vrijednosti. Posljetitelj sada pamti da je varijabli A

### postavljena nulta vrijednost iz predanog polja, varijabli B prva a varijabli C druga (u našem slucaju, prvi

### poziv sve tri varijable postavlja na false). Konacno, posljetitelja šaljemo izrazu kroz metodu accept.

### Rezultat ce biti provodenje izracuna koji potom dohvacamo i ispisujemo.

### Da bi ovo funkcioniralo, nekoliko stvari moramo složiti na korektan nacin.

### Posjetitelju moramo predati popis varijabli, te na neki nacin moramo pamtiti koju vrijednost trenutno ima

### koja varijabla. Za ovo cemo alocirati polje booleovih vrijednosti cija velicina odgovara broju varijabli.

### Uz polje ovih vrijednosti, održavat cemo i mapu koja ime varijable preslikava u redni broj. Primjerice, ako

### nam korisnik preda popis varijabli "A", "E", "Z", u mapi cemo zapamtiti da je A bila na poziciji 0, E na

### poziciji 1 a Z na poziciji 2. Na tim istim pozicijama u polju booleovih vrijednosti pronaci cemo i vrijednost

### koju varijabla ima dodijeljenu.

### Uz ovo, za potrebe izracuna trebat cemo i jedan stog booleovih vrijednosti.

### Evo nekoliko implementacijskih detalja:

**public class** ExpressionEvaluator **implements** NodeVisitor {

```
private boolean [] values;
private Map<String, Integer> positions;
private Stack<Boolean> stack = new Stack<>();
```
```
public ExpressionEvaluator(List<String> variables) {...}
```
```
public void setValues( boolean [] values) {...}
```
**public void** visit(ConstantNode node) {...}
**public void** visit(VariableNode node) {...}
**public void** visit(UnaryOperatorNode node) {...}
**public void** visit(BinaryOperatorNode node) {...}
**public void** start() {...}
**public boolean** getResult() {...}
}

### Kada se posjetitelju preda novi niz vrijednosti (metoda setValues), iskopirat cemo vrijednosti iz dobivenog

### polja u interno polje vrijednosti varijabli.

### Kada posjetitelj dode do cvora koji predstavlja konstantu, on tu konstantu zapisuje na stog. Kada dode na

### cvor koji odgovara varijabli, u mapi cita koji je redni broj dodijeljen toj varijabli, i potom u internom polju

### vrijednosti cita vrijednost te varijable i gura je na stog. Ako mapa nema informaciju o varijabli, baciti

### iznimku IllegalStateException.

### Uocite, u slucajevima kada je naš citav izraz bio jedna konstanta ili jedna varijabla, obilazak "stabla" ce

### rezultirati samo jednim guranjem na stog. Citanjem tog jednog elementa sa stoga dolazimo do vrijednosti

### izraza i to je upravo ono što radi metoda getResult(). Ako na stogu nije tocno jedna vrijednost, nešto ne

### valja i treba baciti iznimku (IllegalStateException). Metoda getResult() pri tome ne briše vrijednost

### sa stoga pa se može pozvati i više puta - vracat ce vrijednost zadnjeg provedenog izracuna, sve dok se stog

### ne obriše pozivom metode start() cime se stog briše i tako priprema za provodenje novog izracuna.

### Dopunite metodu setValues tako da i ona poziva metodu start() cime se posjetitelj automatski priprema

### za provodenje novog izracuna.

### Ostalo je razmotriti što uciti sa cvorovima koji su operatori. Ali tu je situacija vrlo jednostavna: oni naprosto


### sebe šalju svojoj djeci, cime ce u konacnici za svako dijete na stogu nastati po jedna booleova vrijednost.

### Cvor potom sa stoga skida onoliko booleovih vrijednosti koliko ima djece, te vrijednosti kombinira

### predanom strategijom (sjetite se da cvorovi koji modeliraju unarne i binarne operatore u konstruktoru traže i

### strategiju koja zna obraditi jednu odnosno dvije vrijednosti), i konacni rezultat djelovanja operatora

### ponovno guraju na stog, cime je isti spreman ili za dohvat kao konacan rezultat, ili za kombiniranje u nekom

### od operatora koji su u stablu bili na višoj razini.

### Pogledajte sada demonstracijski program ForEachDemo2.java; ubacite ga u projekt i osigurajte da Vam

### daje prikazani izlaz. Program kombinira forEach metodu koju ste napravili u zadatku 4 i posjetitelja

### razvijenog u zadatku 5 kako bi ispisao tablicu istinitosti proizvoljne Booleove funkcije. Pokretanjem

### programa morate dobiti sljedeci ispis.

#### [0, 0, 0] ==> 0

#### [0, 0, 1] ==> 1

#### [0, 1, 0] ==> 0

#### [0, 1, 1] ==> 1

#### [1, 0, 0] ==> 0

#### [1, 0, 1] ==> 1

#### [1, 1, 0] ==> 1

#### [1, 1, 1] ==> 1

## Problem 6.

### U paketu hr.fer.zemris.bf.utils u razred Util dodajte novu staticku metodu:

**public static** Set< **boolean** []> filterAssignments(
List<String> variables, Node expression, **boolean** expressionValue
);

### Metoda prima popis varijabli i jedan izraz, stvara sve kombinacije varijabli, uporabom prethodno napisanog

### posjetitelja racuna vrijednost funkcije, i ako se ona podudara s vrijednosti koja je predana kao treci

### argument ove metode, dodaje tu kombinaciju u skup kombinacija koji na kraju vraca. Skup mora biti tako

### podešen da mu iterator vraca elemente poretkom koji kombinacije imaju u tablici istinitosti. Primjerice, ako

### smo imali funkciju od dvije varijable, i ako je ona bila 1 za kombinacije [true, false] i [false, false], iterator

### mora najprije vratiti [false, false] a potom [true, false]. Razmislite koja Vam implementacija skupa odgovara

### i kako je podesiti da postignete ovakvo ponašanje.

### Pogledajte sada demonstracijski program UtilDemo1.java; ubacite ga u projekt i osigurajte da Vam daje

### prikazani izlaz. Izlaz mora biti:

#### [0, 0, 1]

#### [0, 1, 1]

#### [1, 0, 1]

#### [1, 1, 0]

#### [1, 1, 1]

## Problem 7.

### U paketu hr.fer.zemris.bf.utils u razred Util dodajte još i sljedece staticke metode.

**public static int** booleanArrayToInt( **boolean** [] values);


### Metoda prima polje booleovih vrijednosti i pretvara ga u redni broj retka gdje se ta kombinacija nalazi u

### tablici istinitosti (numeracija ide od 0); npr. za polje [false, false, true, true] vraca 3.

**public static** Set<Integer> toSumOfMinterms(
List<String> variables, Node expression
);
**public static** Set<Integer> toProductOfMaxterms(
List<String> variables, Node expression
);

### Metode vracaju skup brojeva koji predstavljaju minterme odnosno maksterme koje funkcija sadrži. Obje

### funkcije možete lijepo riješiti delegiranjem trecoj privatnoj funkciji koja se pak oslanja na

### filterAssignments i booleanArrayToInt.

### Pogledajte sada demonstracijski program UtilDemo2.java; ubacite ga u projekt i osigurajte da Vam daje

### prikazani izlaz. Izlaz mora biti:

Mintermi f([A, B, C]): [1, 3, 5, 6, 7]
Mintermi f([C, B, A]): [3, 4, 5, 6, 7]

### Primijetite da stablo kao stablo ne zna ništa o mintermima ili makstermima: stablo je samo reprezentacija

### izraza odnosno odreduje kako se dolazi do vrijednosti uz poznato stanje varijabli. Redni brojevi koje

### pridružujemo mintermima i makstermima odredeni su poretkom varijabli koji postavimo na funkciju –

### upravo to ilustrira prethodni primjer.


### Please note. You can consult with your peers and exchange ideas about this homework before you start

### actual coding. Once you open you IDE and start coding, consultations with others (except with me) will be

### regarded as cheating. You can not use any of preexisting code or libraries for this homework (whether it is

### yours old code or someones else). You can use Java Collection Framework and other parts of Java covered

### by lectures; if unsure – e-mail me. Document your code!

### If you need any help, I have reserved a slot for consultations every day from Tuesday to Friday at 1

### PM. Feel free to drop by my office.

### All source files must be written using UTF-8 encoding. All classes, methods and fields (public, private or

### otherwise) must have appropriate javadoc.

### When your complete homework is done, pack it in zip archive with name hw07-0000000000.zip (replace

### zeros with your JMBAG). Upload this archive to Ferko before the deadline. Do not forget to lock your

### upload or upload will not be accepted. Deadline is April 22th 2017. at 06:00 PM.

## You are expected to write tests for Lexer class methods.

## You are encouraged to write tests for other problems.


