package ru.itis.generics.ex5;

//ограничили сверху подклассами 2D
public class Coords<T extends Spaces.TwoD> {
    T[] cords;

    public Coords(T[] cords) {
        this.cords = cords;
    }


    public static void main(String[] args) {
        Spaces.TwoD[] twoDS = {
                new Spaces.TwoD(2, 5),
                new Spaces.TwoD(23, 6),
                new Spaces.TwoD(2, 8)

        };

        Spaces.TreeD[] treeDS = {
                new Spaces.TreeD(2, 5, 6),
                new Spaces.TreeD(23, 6, 6),
                new Spaces.TreeD(2, 8, 9)

        };

        Coords<Spaces.TwoD> twoDCoords = new Coords<>(twoDS);

        BoundedWildCard.showXY(twoDCoords);

//        BoundedWildCard.showXYZ(twoDCoords); так сделать не получиться, так как уже ограничение 3д и его подклассы


        Coords<Spaces.TreeD> treeDCoords = new Coords<>(treeDS);

        BoundedWildCard.showXYZ(treeDCoords);

        // для 4д так же
    }


}
