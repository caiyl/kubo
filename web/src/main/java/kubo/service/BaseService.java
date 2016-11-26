package kubo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T,ID extends Serializable> {
  void setBaseMapper();
  //根据主键删除
  int deleteByPrimaryKey(ID id);
  //添加
  int add(T record);
  //添加后返回主键
  int insertSelective(T record);
  //根据主键查找
  T selectByPrimaryKey(ID id);
  //根据字段查询
  List<T> selectByColumns(Map<String, String> map);
  //更新
  int updateByPrimaryKeySelective(T record);
 // int updateByPrimaryKeyWithBLOBs(T record);
  
  //根据主键更新
  int updateByPrimaryKey(T record);
  //分页
  Map<String, Object> queryByPage(Map<String, Integer> map);
  //统计总数
  int count(Map<String, Integer> map);
  
	//根据主键批量删除
  void batchRemoveByIds(List<ID> idList);
}