package hr.fer.zemris.bf.qmc;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import hr.fer.zemris.bf.utils.Util;

public class Mask {

	private byte[] mask;
	private boolean dontCare;
	private Set<Integer> indexes;
	private int maskHash;
	private boolean combined;
	
	public Mask(int index, int numberOfVariables, boolean dontCare){
		if(numberOfVariables <= 0 || index < 0 || index >= Math.pow(2, numberOfVariables)){
			throw new IllegalArgumentException("Argumenti za konstruktor maske su neispravni.");
		}
		
		mask = Util.indexToByteArray(index, numberOfVariables);
		this.dontCare = dontCare;
		indexes = new TreeSet<>();
		indexes.add(index);
		maskHash = Arrays.hashCode(mask);
	}
	
	public Mask(byte[] values, Set<Integer> indexes, boolean dontCare){
		if(values == null || indexes == null || indexes.isEmpty()){
			throw new IllegalArgumentException("Argumenti za konstruktor maske su neispravni.");
		}
		mask = values;
		this.indexes = new TreeSet<Integer>(indexes);
		this.dontCare = dontCare;
		maskHash = Arrays.hashCode(mask);
	
	}
	
	public boolean isCombined(){
		return combined;
	}
	
	public void setCombined(boolean combined){
		this.combined = combined;
	}
	
	public boolean isDontCare(){
		return dontCare;
	}
	
	public Set<Integer> getIndexes(){
		return indexes;
	}
	
	public int countOfOnes(){
		int counter=0;
		for(int i = 0; i < mask.length; i++){
			if(mask[i] == 1) counter++;
		}
		return counter;
	}
	
	public int size(){
		return mask.length;
	}
	
	public byte getValueAt(int position){
		if(position < 0 || position > mask.length - 1){
			throw new IllegalArgumentException("Neispravna pozicija u metodi getValueAt.");
		}
		return mask[position];
	}
	
	public Optional<Mask> combineWith(Mask other){
		if(mask == null || other.mask.length != mask.length){
			throw new IllegalArgumentException("Predana je null maska metodi combine with.");
		}
		
		Set<Integer> newIndexes = new TreeSet<>();
		
		byte[] newMask = new byte[mask.length];
		boolean changed = false;
		for(int i = 0; i < mask.length; i++){
			if((mask[i] == 0 && other.mask[i] == 1) || (mask[i] == 1 && other.mask[i] == 0)){
				if(changed){
					return Optional.empty();
				}
				newMask[i] = 2;
				newIndexes.addAll(this.indexes);
				newIndexes.addAll(other.indexes);
				changed = true;
			} else if(mask[i] == other.mask[i]){
				newMask[i] = mask[i];
			} else{
				return Optional.empty();
			}
		}
		
		return Optional.of(new Mask(newMask, newIndexes, dontCare == true && other.dontCare == true));
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mask.length; i++){
			if(mask[i] == 2){
				sb.append("-");
			} else{
				sb.append(mask[i]);
			}
		}
		
		sb.append(dontCare ? " D" : "  ");
		sb.append(combined ? " * [" : "   [");
		
		for (Integer integer : indexes) {
			sb.append(integer+", ");
		}
		
		sb.setLength(sb.length() - 2);
		sb.append("]");
		
		return sb.toString();
	}
	
	@Override
	public int hashCode(){
		return maskHash;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Mask) || maskHash != o.hashCode()) return false;
		return Arrays.equals(mask, ((Mask) o).mask);
	}
}
