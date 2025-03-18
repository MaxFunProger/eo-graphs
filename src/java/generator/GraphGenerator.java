/**
 * SPDX-FileCopyrightText: Copyright (c) 2020-2025 Objectionary
 * SPDX-License-Identifier: MIT
 */

import java.util.concurrent.ThreadLocalRandom;

public class GraphGenerator {
    public static final int OUTPUT_TYPE_EDGES = 0;
    public static final int OUTPUT_TYPE_ADJACENCY_LISTS = 1;

    public static String generateEdges(int vNum){
        StringBuilder res = new StringBuilder();
        if(vNum > 1)
            for(int i = 0; i < vNum-1; i++)
                for(int j = i+1; j < vNum; j++){
                    res.append(
                            String.format(
                                    "%d %d %d ",
                                    i, j, ThreadLocalRandom.current().nextInt(1, 100)
                            )
                    );
                }
        return res.toString();
    }

    public static String generateAdjacencyLists(int vNum){
        StringBuilder res = new StringBuilder();
        res.append(String.format("%d ", vNum));
        if(vNum > 1)
            for(int i = 0; i < vNum; i++){
                int nodes_cnt = 0;
                StringBuilder nodes = new StringBuilder();
                for(int j = 0; j < vNum; j++){
                    if(i!=j) {
                        int is_taken = ThreadLocalRandom.current().nextInt(0, 100);
                        if (is_taken % 3 != 0) {
                          ++nodes_cnt;
                          nodes.append(
                                  String.format(
                                          " %d:%d",
                                          j, ThreadLocalRandom.current().nextInt(1, 100)
                                  )
                          );
                        }
                    }
                }
                res.append(String.format("%d ", nodes_cnt));
                res.append(String.format("%d", i));
                // res.append(" ");
                res.append(nodes.toString());
                res.append(" ");
            }
        res.append("\n");
        return res.toString();
    }

    public static void main(String[] args) {
        if(args.length == 2)
            try{
                int outType = Integer.parseInt(args[0]);
                int vNum = Integer.parseInt(args[1]);
                switch (outType){
                    case OUTPUT_TYPE_ADJACENCY_LISTS:
                        System.out.print(generateAdjacencyLists(vNum));
                        break;
                    case OUTPUT_TYPE_EDGES:
                    default:
                        System.out.print(generateEdges(vNum));
                }

            }catch (Exception e){
                System.exit(1);
            }
    }
}
