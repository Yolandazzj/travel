package com.qdu.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Repository
public class GroupDaoImpl implements GroupDao {

    @Autowired
    private SessionFactory sessionFactory;

    //组团游申请表单数据插入到group表中
    @Override
    public void toGroup(int cityId, String gtime, float gprice, int gquatity, String gendTime, String gcontent,String uid,String contact) {
        Date startTime=null;
        Date endTime=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(gtime);

        try {
            startTime=format.parse(gtime);
            endTime= format.parse(gendTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("insert into `bishe`.`group` (`CityId`, `GTime`, `GPrice`, `GQuatity`, `GEndTime`, `GContent`, `UId`, `Contact`) values(?,?,?,?,?,?,?,?)");
        query.setParameter(0,cityId);
        query.setParameter(1, gtime);
        query.setParameter(2, gprice);
        query.setParameter(3,gquatity);
        query.setParameter(4, gendTime);
        query.setParameter(5, gcontent);
        query.setParameter(6, uid);
        query.setParameter(7, contact);
        query.executeUpdate();
    }

    //获取全部组团游列表，分页
    @Override
    public List groupAll(int offset, int length) {
        List groupList=null;
        try{
            Query query=sessionFactory.getCurrentSession().createQuery("from Group");
            query.setFirstResult(offset);
            query.setMaxResults(length);
            groupList=query.list();
        }catch(RuntimeException re){
            throw re;
        }
        return groupList;
    }


    //返回结果的条数
    @Override
    public int getAllRowCount() {
        Query query=sessionFactory.getCurrentSession().createSQLQuery("select COUNT(*) as num from `group`")
                .addScalar("num", StandardBasicTypes.INTEGER)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List groupList = query.list();
        HashMap map = (HashMap) groupList.get(0);
        int num = (int) map.get("num");
        return num;
    }


}