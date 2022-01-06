package cn.hsp.shop.mapper;

import cn.hsp.shop.bean.Cart;
import cn.hsp.shop.bean.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface CartMapper {
    @Select(value = "SELECT g.* FROM cart c,goods g WHERE c.goods_id=g.id AND c.user_id=#{userId}")
    List<Goods> query(@Param("userId") int userId);

//    @Select(value = "select * from goods where id = #{goodsId}")
//    Goods queryById(@Param("goodsId") String goodsId);

    @Insert(value = "insert into cart(id,user_id,goods_id) values (#{id},#{userId}, #{goodsId})")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("goodsId") String goodsId);
//
//    @Update(value = "update blog set title=#{title},content=#{content},lastUpdateTime=now() where id = #{id}")
//    void modify(@Param("id") long id, @Param("title") String title, @Param("content") String content);
//
//    @Delete("delete from blog where id = #{id}")
//    void del(@Param("id") long id);
}
