package ru.itis.generics.ex5;

public class Spaces {

    static class TwoD {
        int x, y;

        public TwoD(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class TreeD extends TwoD {
        int z;

        public TreeD(int x, int y, int z) {
            super(x, y);
            this.z = z;
        }
    }

    static class FourD extends TreeD {
        int t;

        public FourD(int x, int y, int z, int t) {
            super(x, y, z);
            this.t = t;
        }
    }
    
}
