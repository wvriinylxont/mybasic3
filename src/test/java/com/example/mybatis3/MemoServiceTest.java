package com.example.mybatis3;

import com.example.mybatis3.memo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemoServiceTest {
  @Autowired
  private MemoService memoService;

  @Test
  public void saveTest() {
    Memo memo = new Memo(null, "연습", "연습연습", "spring", null);
    System.out.println(memo);

    memoService.save(memo);
    System.out.println(memo);
  }

  @Test
  public void findAllTest() {
    List<Memo> memos = memoService.findAll();
    System.out.println(memos);
    assertNotEquals(0, memos.size());
  }

  @Test
  public void findByMnoTest() {
    assertNotEquals(true, memoService.findByMno(2).isPresent());
  }

  @Test
  public void updateTest() {
    Memo memo = new Memo(2, "변경됐어요", "변경", null,null);
    assertEquals(true, memoService.update(memo));
  }

  @Transactional
  @Test
  public void deleteTest() {
    assertEquals(true, memoService.delete(2));
  }
}
