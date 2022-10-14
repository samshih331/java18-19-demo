package java18.JEP417_Vector_API;

import java.util.Arrays;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class Demo {

	public static void main(String[] args) {
		float[] a = new float[] {0.1f, 0.2f};
		float[] b = new float[] {0.4f, 0.3f};
		float[] c = new float[2];

		//		scalarComputation(a, b, c);
		//		System.out.println(Arrays.toString(c));

		vectorComputation(a, b, c);
		System.out.println(Arrays.toString(c));

	}

	static void scalarComputation(float[] a, float[] b, float[] c) {
		for (int i = 0; i < a.length; i++) {
			c[i] = (a[i] + b[i]);
		}
	}

	static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

	static void vectorComputation(float[] a, float[] b, float[] c) {
		int i = 0;
		int upperBound = SPECIES.loopBound(a.length);
		for (; i < upperBound; i += SPECIES.length()) {
			// FloatVector va, vb, vc;
			var va = FloatVector.fromArray(SPECIES, a, i);
			var vb = FloatVector.fromArray(SPECIES, b, i);
			var vc = va.add(vb);
			vc.intoArray(c, i);
		}
		for (; i < a.length; i++) {
			c[i] = (a[i] + b[i]);
		}
	}
}
