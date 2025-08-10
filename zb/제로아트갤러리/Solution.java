package org.example.ps.zb.제로아트갤러리;

import java.util.ArrayList;
import java.util.List;

class Item {
    int arriveTime;
    int patienceTime;

    public Item(int arriveTime, int patienceTime) {
        this.arriveTime = arriveTime;
        this.patienceTime = patienceTime;
    }
}
class Solution {
    public int solution(int[] arrive, int[] patience) {
        int N = arrive.length;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            items.add(new Item(arrive[i], patience[i]));
        }

        // 도착시간이 빠른 순 -> 인내심이 적은 순으로 정렬
        items.sort(((o1, o2) -> {
            if (o1.arriveTime == o2.arriveTime) {
                return o1.patienceTime - o2.patienceTime;
            }
            return o1.arriveTime - o2.arriveTime;
        }));

        int result = 0;

        // 첫번째 사람은 미리 대기중으로 변경
        int lastArriveTime = items.get(0).arriveTime;
        int lastPatienceTime = items.get(0).patienceTime;
        int curIdx = 1;

        while(curIdx < N) {
            int arriveTime = items.get(curIdx).arriveTime;
            int patienceTime = items.get(curIdx).patienceTime;

            // 현재 도착한 사람이 기다리는 사람과 만난 경우 -> 관람
            if (lastArriveTime <= arriveTime && arriveTime <= lastArriveTime + lastPatienceTime) {
                result += 2;
                curIdx += 1;
                if (curIdx >= N) break;
                lastArriveTime = items.get(curIdx).arriveTime;
                lastPatienceTime = items.get(curIdx).patienceTime;
                curIdx += 1;
                if (curIdx >= N) break;
            } else { // 현재 도착한 사람이 기다려야 하는 경우
                lastArriveTime = arriveTime;
                lastPatienceTime = patienceTime;
                curIdx += 1;
            }
        }

        return result;
    }
}
