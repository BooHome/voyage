package com.ihere.voyage.mapper;

import com.ihere.voyage.entity.PuzzleMember;
import com.ihere.voyage.entity.PuzzleMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PuzzleMemberMapper {
    long countByExample(PuzzleMemberExample example);

    int deleteByExample(PuzzleMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PuzzleMember record);

    int insertSelective(PuzzleMember record);

    List<PuzzleMember> selectByExample(PuzzleMemberExample example);

    PuzzleMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PuzzleMember record, @Param("example") PuzzleMemberExample example);

    int updateByExample(@Param("record") PuzzleMember record, @Param("example") PuzzleMemberExample example);

    int updateByPrimaryKeySelective(PuzzleMember record);

    int updateByPrimaryKey(PuzzleMember record);
}