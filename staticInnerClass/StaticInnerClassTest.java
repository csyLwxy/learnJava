package staticInnerClass;

public class StaticInnerClassTest{
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; ++i){
            d[i] = 100 * Math.random();
        }
        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        System.out.println("min = " + p.getMin());
        System.out.println("max = " + p.getMax());
    }
}

class ArrayAlg{
    /**
     * A pair of floating-point numbers
     */
    public static class Pair{
        private double min;
        private double max;

        /**
         * Constructor a pair from two floating-point numbers
         * @param min the min number
         * @param max the max number
         */
        public Pair(double min, double max){
            this.min = min;
            this.max = max;
        }

        /**
         * @return the min
         */
        public double getMin() {
            return min;
        }

        /**
         * @return the max
         */
        public double getMax() {
            return max;
        }
    }

    /**
     * Computes both the minimum and the maximum of an array
     * @param values an array of floating-point numbers
     * @return a pair 
     */
    public static Pair minmax(double[] values){
        double min = values[0];
        double max = values[0];
        for (double num : values){
            min = num < min ? num : min;
            max = num > max ? num : max;
        }
        return new Pair(min, max);
    }
}