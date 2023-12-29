package IfChoose;




public class Edge {
        Node from; // 边的起点
        Node to; // 边的终点
        int weight; // 边的权重

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

