package kubo.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kubo.dao.BaseMapper;

public abstract class AbstractService<T, ID extends Serializable> implements BaseService<T, ID> {
	
	
	private BaseMapper<T, ID> baseMapper;

	public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public int deleteByPrimaryKey(ID id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(ID id) {
		return baseMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<T> selectByColumns(Map<String,String> map){
		return baseMapper.selectByColumns(map);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	// @Override
	// public int updateByPrimaryKeyWithBLOBs(T record) {
	// return baseMapper.updateByPrimaryKeyWithBLOBs(record);
	// }
	@Override
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int add(T record) {
		return baseMapper.insert(record);
	}

	@Override
	public Map<String, Object> queryByPage(Map<String, Integer> map) {
		Map<String,Object> pageMap = new HashMap<String,Object>();
	    pageMap.put("rows", baseMapper.selectByPager(map));
	    pageMap.put("totalSize", baseMapper.count(map));
		return pageMap;
		
	}
	
	@Override
	public int count(Map<String,Integer> map){
		return baseMapper.count(map);
	}
	
	@Override
	public void batchRemoveByIds(List<ID> idList){
		baseMapper.batchDeleteByIds(idList);
	}
}
