package kubo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T ,ID extends Serializable> {

	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(ID id);

	/**
	 * 插入
	 */
	int insert(T record);

	/**
	 * 获取刚刚插入记录的主键值
	 */
	int insertSelective(T record);

	/**
	 * 根据主键查找
	 */
	T selectByPrimaryKey(ID id);
	
	/**
	 * 根据字段查询
	 * @param map
	 * @return
	 */
	List<T> selectByColumns(Map<String, String> map);

	/**
	 * 是更新新的model中不为空的字段
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 将为空的字段在数据库中置为NULL
	 */
	int updateByPrimaryKey(T record);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	List<T> selectByPager(Map<String, Integer> map);
	
	/**
	 * 统计总数
	 * @param map
	 * @return
	 */
	int count(Map<String, Integer> map);
	
	//根据主键批量删除
	void batchDeleteByIds(List<ID> idList);
}
