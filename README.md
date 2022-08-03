### 待办

- 基于nio，netty，rpc框架实现客户端、服务端
- 服务端采用分布式架构、peer to peer架构；分布式架构专门应对敏感词剧增的业务场景；peer2peer架构适用于敏感词不多的业务场景
- 查询时，必须是O(1)的时间复杂度；
- 目前分词采用IK分词器（中文），英文等其他语言采用什么分词器？
- 估算一下10w词汇占用内存情况，100w词汇占用内存情况，1000w词汇占用内存情况
- 评估是否可以将所有的敏感词词汇加载进内存中，如果全部敏感词无法加载进内存的解决方案是什么？
- 评估与市面上产品的优劣势