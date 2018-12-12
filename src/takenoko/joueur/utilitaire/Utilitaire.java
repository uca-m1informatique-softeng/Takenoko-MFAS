package takenoko.joueur.utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilitaire {


    public static List<List<Integer>> permutations(int n) { // could these be
        // sets?
        final List<List<Integer>> v=new ArrayList<>();
        if(n<=1) {
            List<Integer> a=new ArrayList<>(1);
            a.add(0);
            v.add(a);
        } else {
            final List<List<Integer>> v1=permutations(n-1);
            int nMius1Factorial=v1.size();
            for(int i=0;i<nMius1Factorial;i++) {
                List<Integer> a=v1.get(i);
                Integer[] aa=a.toArray(new Integer[0]);
                for(int j=0;j<n;j++) {
                    Integer[] a2a=new Integer[n];
                    System.arraycopy(aa,0,a2a,0,j);
                    a2a[j]=n-1;
                    System.arraycopy(aa,j,a2a,j+1,n-j-1);
                    v.add(Arrays.asList(a2a));
                }
            }
        }
        return v;
    }
}
