# AStar
MyAStar   @wjcx-zh
1.通过对A*算法的查阅，了解到其基本形态有两种：基于曼哈顿距离，基于欧式距离
2.了解前人的相关研究
3.认识到A*算法的两个重要组成成分：a,启发函数的设计；b,辅助表结构（open，close）
4.研究辅助表的相关操作：
    open：存放算法搜索的结点信息，主要操作是添加和删除
    close:存放从open访问的结点的信息，主要操作是添加和搜索
5.在方向估值的基础上，提出了基于障碍威胁估值的启发函数设计，并与方向信息结合，减少了算法对于结点的搜索
6.对辅助表进行优化操作，对open使用了最小堆结构；对close使用了平衡树的结构Size-Balanced-Tree.
7.基于eclipse工具设计了一个算法测试界面，（应用了单例模式和工厂模式），对三种A*算法进行了实验测试。
8.搜集测试数据，通过对比测试数据，得出效率的问题
9.进行一定的算法复杂度分析
