/**
 * SPDX-FileCopyrightText: Copyright (c) 2020-2025 Objectionary
 * SPDX-License-Identifier: MIT
 */

import java.util.Arrays;

public class Dijkstra {
  public static void dijkstra(int vNum, int[] GR, int st) {
    int[] distance = new int[vNum];
    int count, index = 0, i, u;
    int[] path = new int[vNum];
    boolean[] visited = new boolean[vNum];
    for (i = 0; i < vNum; i++) {
      distance[i] = Integer.MAX_VALUE;
      visited[i] = false;
      path[i] = -1;
    }
    path[st] = st;
    distance[st] = 0;
    for (count = 0; count < vNum - 1; count++) {
      int min = Integer.MAX_VALUE;
      for (i = 0; i < vNum; i++)
        if (!visited[i] && distance[i] <= min) {
          min = distance[i];
          index = i;
        }
      u = index;
      visited[u] = true;
      for (i = 0; i < vNum; i++)
        if (!visited[i] && (GR[u * vNum + i] != 0) &&
            (distance[u] != Integer.MAX_VALUE) &&
            (distance[u] + GR[u * vNum + i] < distance[i])) {
          distance[i] = distance[u] + GR[u * vNum + i];
          path[i] = u;
        }
    }
    System.out.print("Paths: ");
    for (i = 0; i < vNum; i++)
      System.out.print(path[i] + " ");
  }

  public static void main(String[] args) {
    if (args.length > 0) {
      int i = 1;
      int vNum = Integer.parseInt(args[0]);
      int[] GR = new int[vNum * vNum];
      Arrays.fill(GR, 0);
      while (i < args.length) {
        int number_of_edges = Integer.parseInt(args[i]);
        int u = Integer.parseInt(args[i + 1]);
        i += 2;
        for (int j = 0; j < number_of_edges; ++j) {
          int v, w;
          String[] tmp = args[i + j].split(":");
          v = Integer.parseInt(tmp[0]);
          w = Integer.parseInt(tmp[1]);
          GR[u * vNum + v] = w;
        }
        i += number_of_edges;
      }
      dijkstra(vNum, GR, 0);
    }
  }
}
