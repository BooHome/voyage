package com.ihere.voyage.mapper;

import com.ihere.voyage.entity.PuzzleFile;
import com.ihere.voyage.entity.PuzzleFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PuzzleFileMapper {
    long countByExample(PuzzleFileExample example);

    int deleteByExample(PuzzleFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PuzzleFile record);

    int insertSelective(PuzzleFile record);

    List<PuzzleFile> selectByExample(PuzzleFileExample example);

    PuzzleFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PuzzleFile record, @Param("example") PuzzleFileExample example);

    int updateByExample(@Param("record") PuzzleFile record, @Param("example") PuzzleFileExample example);

    int updateByPrimaryKeySelective(PuzzleFile record);

    int updateByPrimaryKey(PuzzleFile record);
}