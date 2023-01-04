package com.example.vscapp01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vscapp01.dto.MemberDto;
import com.example.vscapp01.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	//https://goddaehee.tistory.com/209
	//비워있어도 잘 작동함.
    // long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함
	
	// findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    //public List<MemberEntity> findById(Long id);

    public List<MemberEntity> findByName(String name);

    //like검색도 가능
    public List<MemberEntity> findByNameLike(String keyword);

    @Query(value = "select mbr_no, id, name from fa_memberinfo where name = :name", nativeQuery=true)
    List<MemberEntity> searchParamRepo(@Param("name") String name);
    //Controller > Service > Impl > Dao > Mapper(xml)
    
    Optional<MemberDto> findByEmail(String email);
    boolean existsByEmail(String email);

    MemberEntity findByMemberId(String username);
}
