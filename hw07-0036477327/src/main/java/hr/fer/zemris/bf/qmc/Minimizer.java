package hr.fer.zemris.bf.qmc;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

public class Minimizer {

	private static final Logger LOG = Logger.getLogger("hr.fer.zemris.bf.qmc");
	private Set<Integer> mintermSet;
	private Set<Integer> dontCareSet;
	private List<String> variables;

	public Minimizer(Set<Integer> mintermSet, Set<Integer> dontCareSet, List<String> variables) {
		if (mintermSet == null || dontCareSet == null || variables == null) {
			throw new IllegalArgumentException("Konstruktoru minimizatora je predana barem jedna null varijabla.");
		}

		for (Integer integer : mintermSet) {
			if (dontCareSet.contains(integer)) {
				throw new IllegalArgumentException("MintermSet i dontCareSet imaju zajedničkih vrijednosti.");
			}
		}

		this.mintermSet = mintermSet;
		this.dontCareSet = dontCareSet;
		this.variables = variables;

		checkValidIndexes(mintermSet, Math.pow(2, variables.size()));
		checkValidIndexes(dontCareSet, Math.pow(2, variables.size()));

		Set<Mask> primCover = findPrimaryImplicants(convertIndexesToMasks());
		chooseMinimalCover(primCover);

		for (Mask mask : primCover) {
			System.out.println(mask);
		}
	}

	private List<Set<Mask>> chooseMinimalCover(Set<Mask> primCover) {
		Mask[] implicants = primCover.toArray(new Mask[primCover.size()]);
		Integer[] minterms = mintermSet.toArray(new Integer[mintermSet.size()]);
		Map<Integer, Integer> mintermToColumnMap = new HashMap<>();
		for (int i = 0; i < minterms.length; i++) {
			Integer index = minterms[i];
			mintermToColumnMap.put(index, i);
		}
		
		boolean[][] table = buildCoverTable(implicants, minterms, mintermToColumnMap);
		boolean[] coveredMinterms = new boolean[minterms.length];
		
		Set<Mask> importantSet = selectImportantPrimaryImplicants(implicants, mintermToColumnMap, table,
				coveredMinterms);
		
		List<Set<BitSet>> pFunction = buildPFunction(table, coveredMinterms);

		
		Set<BitSet> minset = findMinimalSet(pFunction);
		
		List<Set<Mask>> minimalForms = new ArrayList<>();
		for (BitSet bs : minset) {
			Set<Mask> set = new LinkedHashSet<>(importantSet);
			bs.stream().forEach(i -> set.add(implicants[i]));
			minimalForms.add(set);
		}
		return minimalForms;
	}

	private Set<BitSet> findMinimalSet(List<Set<BitSet>> pFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Set<BitSet>> buildPFunction(boolean[][] table, boolean[] coveredMinterms) {
		List<Set<BitSet>> list = new ArrayList<>();
		
		for(int i = 0; i < coveredMinterms.length; i++){
			if(coveredMinterms[i]) continue;
			
			list.add(getIndexesFromColumn(table, i));
		}
		
		return list;
	}

	private Set<BitSet> getIndexesFromColumn(boolean[][] table, int j) {
		Set<BitSet> set = new HashSet<>();
		
		BitSet toAdd = new BitSet();
		for(int i = 0; i < table[0].length; i++){
			if(table[i][j]){
				toAdd.set(i, true);
			}
		}
		set.add(toAdd);
		return set;
	}

	private Set<Mask> selectImportantPrimaryImplicants(Mask[] implicants, Map<Integer, Integer> mintermToColumnMap,
			boolean[][] table, boolean[] coveredMinterms) {
		
		Set<Mask> importantImplicants = new HashSet<>();
		
		for(int i = 0; i < implicants.length; i++){
			int index1 = getIndexOfFirstTrueInColumn(table, i, coveredMinterms.length);
			int index2 = getIndexOfLastTrueInColumn(table, i, coveredMinterms.length);
			
			if(index1 == index2 && index1 != -1){
				importantImplicants.add(implicants[i]);
				for (Integer index : implicants[i].getIndexes()) {
					coveredMinterms[mintermToColumnMap.get(index)] = true;
				}
			}
		}
		
		return importantImplicants;
	}

	private int getIndexOfFirstTrueInColumn(boolean[][] table, int j, int n) {
		for(int i = 0; i < n; i++){
			if(table[i][j]) return i;
		}
		return -1;
	}
	
	private int getIndexOfLastTrueInColumn(boolean[][] table, int j, int n) {
		for(int i = n - 1; i >= 0; i--){
			if(table[i][j]) return i;
		}
		return -1;
	}

	private boolean[][] buildCoverTable(Mask[] implicants, Integer[] minterms,
			Map<Integer, Integer> mintermToColumnMap) {
		
		boolean[][] coverTable = new boolean[implicants.length][minterms.length];
		
		for (int i = 0; i < implicants.length; i++){
			Mask mask = implicants[i];
			for (Integer index : mask.getIndexes()) {
				Integer columnNumber = mintermToColumnMap.get(index);
				if(columnNumber != null){
					coverTable[i][columnNumber] = true;
				}
			}
		}
		
		return coverTable;
	}

	private Set<Mask> convertIndexesToMasks() {
		Set<Mask> set = new HashSet<>();

		for (Integer index : mintermSet) {
			set.add(new Mask(index, variables.size(), false));
		}

		for (Integer index : dontCareSet) {
			set.add(new Mask(index, variables.size(), true));
		}

		return set;
	}

	private Set<Mask> findPrimaryImplicants(Set<Mask> allMasks) {
		Map<Integer, List<Mask>> treeMap = new TreeMap<>();
		for (Mask mask : allMasks) {
			int key = mask.countOfOnes();
			List<Mask> list = treeMap.get(key);
			if (list == null) {
				list = new ArrayList<>();
			}

			list.add(mask);
			treeMap.put(key, list);
		}

		Set<Mask> newMasks = new HashSet<>();
		for (Integer numOfOnes : treeMap.keySet()) {
			newMasks.addAll(tryToCombine(treeMap.get(numOfOnes), treeMap.get(numOfOnes + 1)));
		}

		Set<Mask> toReturn = null;
		if (!newMasks.isEmpty()) {
			toReturn = findPrimaryImplicants(newMasks);
		}

		if (toReturn == null) {
			toReturn = new HashSet<>();
		}

		for (Entry<Integer, List<Mask>> entry : treeMap.entrySet()) {
			for (Mask mask : entry.getValue()) {
				if (!mask.isCombined()) {
					toReturn.add(mask);
				}
			}
		}

		return toReturn;
	}

	private Set<Mask> tryToCombine(List<Mask> list, List<Mask> otherList) {
		Set<Mask> set = new HashSet<>();
		if (list == null || otherList == null)
			return set;

		for (Mask mask : list) {
			for (Mask otherMask : otherList) {
				Optional<Mask> opt = mask.combineWith(otherMask);
				if (opt.isPresent()) {
					mask.setCombined(true);
					otherMask.setCombined(true);
					set.add(opt.get());
				}
			}
		}

		return set;
	}

	private void checkValidIndexes(Set<Integer> set, double d) {
		for (Integer integer : set) {
			if (integer >= d) {
				throw new IllegalArgumentException("Set sadrži indeks koji je veći od " + d);
			}
		}
	}
}
