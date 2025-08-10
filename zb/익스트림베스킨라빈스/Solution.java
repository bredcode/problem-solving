package org.example.ps.zb.익스트림베스킨라빈스;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private int r;
    private int target;

    // 메모이제이션을 위한 맵
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean solution(int target, int r) {
        this.r = r;
        this.target = target;

        // 시작 시 게임 수는 0부터, 사용할 수 있는 수는 1부터 r까지
        boolean[] used = new boolean[r + 1];
        return dfs(0, used);
    }

    // 현재 게임 상태에서 첫 번째 사람이 이길 수 있는지 재귀적으로 판단
    private boolean dfs(int currentSum, boolean[] used) {
        if (currentSum >= target) {
            return false; // currentSum은 이전 사람이 만들어온 합계이며 게임이 끝났으므로 현재 사람이 패배(상대방 승)
        }

        // 현재 상태를 문자열로 변환하여 메모이제이션 사용
        String state = currentSum + ":" + getUsedState(used);
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        // 현재 사람이 이길 수 있는지 판단
        for (int i = 1; i <= r; i++) {
            if (!used[i]) { // 아직 사용하지 않은 수를 선택
                used[i] = true; // 수를 사용
                boolean opponentCanWin = dfs(currentSum + i, used);
                used[i] = false; // 수를 다시 사용 가능하게 돌려놓음

                // 상대방이 이길 수 없으면 현재 사람이 승리
                if (!opponentCanWin) {
                    memo.put(state, true);
                    return true;
                }
            }
        }

        // 위 경우에서 현재 사람이 어떻게 해도 이기는 경우가 나오지 않는 경우,
        // 즉, 모든 경우를 살펴봤을 때 첫 번째 사람이 이길 수 없으면 false
        memo.put(state, false);
        return false;
    }

    // 현재 사용한 수의 상태를 문자열로 변환 (메모이제이션을 위한 키로 사용)
    private String getUsedState(boolean[] used) {
        StringBuilder sb = new StringBuilder();
        for (boolean b : used) {
            sb.append(b ? "1" : "0");
        }
        return sb.toString();
    }

}
