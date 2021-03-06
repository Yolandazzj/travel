package com.qdu.dao;

import com.qdu.pojo.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class EssayDaoImpl implements EssayDao {

    @Autowired
    private SessionFactory sessionFactory;

    //数据插入到essay表中
    @Override
    public void toEssay(String eTitle, String uid, String eContent, int cityId) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("insert into `bishe`.`essay` (`eTitle`, `uid`, `eContent`, `CityId`) values(?,?,?,?)");
        query.setParameter(0, eTitle);
        query.setParameter(1, uid);
        query.setParameter(2, eContent);
        query.setParameter(3, cityId);
        query.executeUpdate();
    }

    //获取热门攻略1--7
    @Override
    public Essay hotEssay1() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(0);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public Essay hotEssay2() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(1);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public Essay hotEssay3() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(2);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public Essay hotEssay4() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(3);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public Essay hotEssay5() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(4);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public Essay hotEssay6() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(5);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public Essay hotEssay7() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(6);
        query.setMaxResults(1);
        return (Essay) query.uniqueResult();
    }

    @Override
    public List essayByScore(int cityId) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from Essay where categoryId=? order by escore desc")
                .addScalar("essayId", StandardBasicTypes.STRING)
                .addScalar("etitle", StandardBasicTypes.STRING)
                .addScalar("uid", StandardBasicTypes.INTEGER)
                .addScalar("uname", StandardBasicTypes.STRING)
                .addScalar("econtent", StandardBasicTypes.STRING)
                .addScalar("escore", StandardBasicTypes.INTEGER)
                .addScalar("etime", StandardBasicTypes.DATE)
                .setParameter(0, cityId)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public List essayByTime(int cityId) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from Essay where categoryId=? order by etime desc")
                .addScalar("essayId", StandardBasicTypes.STRING)
                .addScalar("etitle", StandardBasicTypes.STRING)
                .addScalar("uid", StandardBasicTypes.INTEGER)
                .addScalar("uname", StandardBasicTypes.STRING)
                .addScalar("econtent", StandardBasicTypes.STRING)
                .addScalar("escore", StandardBasicTypes.INTEGER)
                .addScalar("etime", StandardBasicTypes.DATE)
                .setParameter(0, cityId)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public void reportUser(String uid) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update  userinfo set isBan=0 where uid=?");
        query.setString(0, uid);
        query.executeUpdate();
    }

    @Override
    public List getCityList() {
        Query query = sessionFactory.getCurrentSession().createQuery("from City");
        return query.list();
    }

    /**
     * 分页查询：根据用户查找用户所有
     * <p>
     * //     * @param hql 查询的条件
     *
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return 返回查询记录集合
     */
    @SuppressWarnings("unchecked")
    @Override
    public List queryForPage(int offset, int length, String uid) {
        List entitylist = null;
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select uid,etitle,escore,etime,essayId from Essay where uid=?");
            query.setParameter(0, uid);
            query.addScalar("uid", StandardBasicTypes.STRING);
            query.addScalar("etitle", StandardBasicTypes.STRING);
            query.addScalar("escore", StandardBasicTypes.INTEGER);
            query.addScalar("etime", StandardBasicTypes.TIMESTAMP);
            query.addScalar("essayId", StandardBasicTypes.INTEGER);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.setFirstResult(offset);
            query.setMaxResults(length);
            entitylist = query.list();

        } catch (RuntimeException re) {
            throw re;
        }

        return entitylist;
    }

    //返回结果的条数
    @Override
    public int getAllRowCount() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select COUNT(*) as num from `essay`")
                .addScalar("num", StandardBasicTypes.INTEGER)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//需要关联一张表，未改
        List essayList = query.list();
        HashMap map = (HashMap) essayList.get(0);
        int num = (int) map.get("num");
        return num;
    }

    @Override
    public List essayAll(int offset, int length) {
        List essayList = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from Essay");//需要关联，未改
            query.setFirstResult(offset);
            query.setMaxResults(length);
            essayList = query.list();
        } catch (RuntimeException re) {
            throw re;
        }
        return essayList;
    }

    @Override
    public Essay essayDetails(int essayId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay where essayId=?");
        query.setParameter(0, essayId);
        return (Essay) query.uniqueResult();
    }

    //根据游记ID获取所在城市的名字；（essayId  -> cityId -> cityName）;
    @Override
    public List<City> cityInfo(int essayId) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select c.cityId,c.cityImage,c.cityName from essay e,city c where  e.CityId=c.CityId and e.essayId=?");
        query.addScalar("cityId", StandardBasicTypes.INTEGER);
        query.addScalar("cityName", StandardBasicTypes.STRING);
        query.addScalar("cityImage", StandardBasicTypes.STRING);
        query.setParameter(0, essayId);
        return query.list();
    }

    //根据游记ID获取评论；
    @Override
    public List<Essaycomment> essayComment(int essayId) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select c.eCommentId,c.uid,c.eCommentContent,c.eCommentScore,c.eCommentTime from Essay e,Essaycomment c where e.essayId=c.essayId and e.essayId=?");
        query.setParameter(0, essayId);
        query.addScalar("eCommentId", StandardBasicTypes.INTEGER);
        query.addScalar("uid", StandardBasicTypes.STRING);
        query.addScalar("eCommentContent", StandardBasicTypes.STRING);
        query.addScalar("eCommentScore", StandardBasicTypes.INTEGER);
        query.addScalar("eCommentTime", StandardBasicTypes.TIMESTAMP);
        return query.list();
    }

    @Override
    public void thumb_essay(int essayId) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update  essay set escore=escore+1 where essayId=?");
        query.setInteger(0, essayId);
        query.executeUpdate();
    }

    @Override
    public void thumb_comment(int eCommentId) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update  essayComment set eCommentScore=eCommentScore+1 where eCommentId=?");
        query.setInteger(0, eCommentId);
        query.executeUpdate();
    }

    @Override
    public void toEssayComment(int essayId, String uid, String eCommentContent) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("insert into Essaycomment (essayId, UId, eCommentContent) values(?,?,?) ");
        query.setParameter(0, essayId);
        query.setParameter(1, uid);
        query.setParameter(2, eCommentContent);
        query.executeUpdate();
    }

    //获取最新的游记评论，ajax追加
    @Override
    public Essaycomment getEssayCommentById() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essaycomment order by eCommentId desc");
        query.setMaxResults(1);
        return (Essaycomment) query.uniqueResult();
    }

    @Override
    public List<Essay> getEssayMineList(String uid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay where uid=?");
        query.setParameter(0, uid);
        return query.list();

    }

    @Override
    public void deleteEssay(int essayId) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE from Essay where essayId=?");
        query.setInteger(0, essayId);
        query.executeUpdate();
        System.out.println(essayId + "wwwwwwwwwww");
//        boolean flag = false;
//        Session session = sessionFactory.openSession();
//        try {
//            String hql = "delete from essay where essayId=?";
//            Query query=session.createQuery(hql);
//            query.setInteger(0, essayId);
//            query.executeUpdate();
//            flag = true;
//            session.flush();
//        }catch(Exception e){
//            e.printStackTrace();
//            flag=false;
//        }
//        System.out.println("flag====="+flag);
//        return flag;
    }

    //首页显示：根据评分排序留言


    @Override
    public List indexHotEssay() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Essay order by escore desc");
        query.setFirstResult(0);
        query.setMaxResults(8);
        return query.list();
    }


}
