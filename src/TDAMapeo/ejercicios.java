package TDAMapeo;

import java.util.Iterator;

public class ejercicios {

	public static <K,V> boolean contenido( Map<K,V> M1, Map<K,V> M2) {
		boolean toreturn=true;
		
		if (M1.size()<=M2.size()) {
			Iterator<K> it= M1.keys().iterator();
			try {
				while (it.hasNext() && toreturn)
					if (M2.get(it.next())==null)
						toreturn=false;
			}
			catch (InvalidKeyException e) {}
		}
		else
			toreturn=false;
		return toreturn;
	}
	
	public static <K,V> void acomodar(Map<K,V> mapeo){
		
		Iterable<Entry<K,V>> it= mapeo.entries();
		Map<V,K> mapeosinimagenesiguales= new MapeoHashAbierto<V,K>();
		try {
			for (Entry<K,V> e:it) {
				mapeosinimagenesiguales.put(e.getValue(), e.getKey());
				mapeo.remove(e.getKey());
			}
		}
		catch (InvalidKeyException x) {}
		Iterable<Entry<V,K>> it2= mapeosinimagenesiguales.entries();
		try {
			for (Entry<V,K> e:it2) {
				mapeo.put(e.getValue(), e.getKey());
			}
		}
		catch (InvalidKeyException x) {}
	}

	public static void main (String [] args) {
		Map<Integer,Integer> M1= new MapeoHashAbierto<Integer,Integer>();
		
		try {
			M1.put(14,123);
			M1.put(5, 123);
			M1.put(42, 456);
			M1.put(32, 456);
			M1.put(15, 32);
		}
		catch (InvalidKeyException e) {}
		
		for (Integer i:M1.values()) {
			System.out.println(i);
		}
		
		acomodar(M1);
		
		System.out.println("_______________");
		for (Integer i:M1.values()) {
			System.out.println(i);
		}
	}
}
