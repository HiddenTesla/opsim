package com.op.opsim.offline;

import java.util.Random;

public class Puzzle41 {

    private Random random = new Random();

    private boolean[] slots;
    private int position;

    public static void main(String[] args) {
        int size = args.length < 1? 5: Integer.parseInt(args[0]);
        Puzzle41 p = new Puzzle41(size);
        System.out.println(p);
    }

    public Puzzle41(int n) {
        this.position = random.nextInt(n);
        this.slots = new boolean[n];
        for (int i = 0; i < n; i++) {
            slots[i] = random.nextBoolean();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < slots.length; i++) {
            sb.append(' ');
            sb.append(slots[i]? '1': '0');
        }
        sb.append("]\n");
        for (int i = 1; i <= position + 1; i++) {
            sb.append("  ");
        }
        sb.append('^');
        return sb.toString();
    }
}
