package com.op.opsim.offline;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Puzzle41 {

    private Random random = new Random();

    private boolean[] slots;
    private boolean on;
    private int position;

    private Set<String> statusSet;

    public static void main(String[] args) {
        int size = args.length < 1? 5: Integer.parseInt(args[0]);
        Puzzle41 p = new Puzzle41(size);
        p.solve();
    }

    public Puzzle41(int n) {
        this.position = random.nextInt(n);
        this.slots = new boolean[n];
        this.on = true;
        this.statusSet = new HashSet<>();
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
        sb.append(on? '1': '0');
        return sb.toString();
    }

    private void toggle() {
        on ^= true;
        for (int i = 0; i < slots.length; i++) {
            slots[i] ^= true;
        }
    }

    public void printToStdout() {
        System.out.println(toString());
    }

    public void solve() {
        int round = 0;
        while(!isSolved()) {
            System.out.println("Round " + round);
            printToStdout();

            String s = toString();
            if (statusSet.contains(s)) {
                System.err.println("Not solved after " + round + " steps");
                break;
            }
            statusSet.add(s);

            round++;
            if (isAllOff()) {
                toggle();
                continue;
            }

            int nextPosition = (position + 1) % slots.length;
            boolean current = slots[position];
            boolean next = slots[nextPosition];

            if (current == on) {
                System.out.println("Moving directly");
            }
            else {
                System.out.println("Toggling before Moving");
                toggle();
            }
            printToStdout();

            position = nextPosition;
            if (slots[position] != on) {
                System.out.println("Swapping");
                slots[position] ^= true;
            }
            else {
                System.out.println("No Swapping");
            }
        }
        printToStdout();
    }

    private boolean isSolved() {
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i])
                return false;
        }
        return true;
    }

    private boolean isAllOff() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i])
                return false;
        }
        return true;
    }
}
