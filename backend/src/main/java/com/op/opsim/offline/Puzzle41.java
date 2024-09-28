package com.op.opsim.offline;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


// 原神4.1解谜，位于枫丹科学院区西侧海岸北部。
// 虽然是原神，但本解密与元素反应无关，反而可以抽象成一个数学问题。
// 有以下物件：
// - 5个水方碑，状态为锁定或解锁；
// - 一个晶体，状态也为锁定或解锁；
// - 一个开关，游戏中其状态为橙或蓝，实际无影响，因此认为其为无状态；
// 规律：
// 5个水方碑形成一个环。
// 晶体位于某一个水方碑处，每次移动都会前往下一处。
// 若晶体的目标位的水方碑状态相同，则不变，否则两者交换状态。也可以理解为无条件与目标位的水方碑交换状态。
// 击打中间的开关会反转所有的水方碑，而晶体状态保持不变。
// 当所有水方碑都解锁时，解谜完成。
// 解法：
// 若所有水方碑均为锁定状态，则击打开关，解密完成。
// 若晶体与当前水方碑状态不同，则需击打开关后再移动晶体，否则直接移动晶体。
// 如上步往复，一定能在至多5步内完成。

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
