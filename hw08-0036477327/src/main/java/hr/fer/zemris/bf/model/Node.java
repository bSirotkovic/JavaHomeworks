package hr.fer.zemris.bf.model;

/**
 * Sučelje koje predstavlja jedan čvor koji će biti gradivni element
 * stabla koje će nastati kao rezultat parsiranja
 * binarnog izraza metodom rekurzivnog spusta.
 * @author Branko
 *
 */
public interface Node {

	/**
	 * Metoda kojom svaki čvor potvrđuje i pušta primljeni NodeVisitor da ga posjeti, tj.
	 * da odradi što god ima.
	 * @param visitor Posjetitelj prolazi kroz čvorove da obavlja njemu poznatu strategiju.
	 */
	public void accept(NodeVisitor visitor);
}
