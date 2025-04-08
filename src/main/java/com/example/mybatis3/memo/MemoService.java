package com.example.mybatis3.memo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MemoService {
  @Autowired
  private MemoDao memoDao;

  // 글을 작성 -> 리스트로 이동? 작성한 글읽기로 이동
  public int save(Memo memo) {
    // 현재 memo의 mno는 값이 없다
    
    // DAO에서 @SelectKey를 이용해서 memo의 mno에 시퀀스값을 저장
    memoDao.save(memo);

    // dao호출이 끝나고 나면, dao가 생성해서 저장한 mno값이 memo에 들어있다
    return memo.getMno();
  }

  public List<Memo> findAll() {
    return memoDao.findAll();
  }
  public Optional<Memo> findByMno(int mno) {
    return memoDao.findByMno(mno);
  }

  public boolean update(Memo memo) {
    return memoDao.update(memo)>0;
  }


  public boolean delete(int mno) {
    return memoDao.delete(mno)>0;
  }
}
