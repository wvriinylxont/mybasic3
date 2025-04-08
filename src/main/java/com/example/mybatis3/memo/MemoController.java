package com.example.mybatis3.memo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class MemoController {
  // 이거 빼먹으면 메모서비스가 없음
  @Autowired
  private MemoService memoService;

  @GetMapping("/memo/list")
  public ModelAndView findAll() {
    return new ModelAndView("memo/list").addObject("memos", memoService.findAll());
  }

  @GetMapping("/memo/read")
  public ModelAndView findByMno(@RequestParam Integer mno) {
    Optional<Memo> result = memoService.findByMno(mno);
    if(result.isEmpty())
      return new ModelAndView("redirect:/memo/list");
    return new ModelAndView("memo/read").addObject("memo", result.get());
  }

  @GetMapping("/memo/write")
  public ModelAndView save() {
    return new ModelAndView("memo/write");
  }

  @PostMapping("/memo/write")
  public ModelAndView save(@ModelAttribute Memo memo) {
    int mno = memoService.save(memo);
    return new ModelAndView("redirect:/memo/read?mno=" + mno);
  }

  @PostMapping("/memo/update")
  public ModelAndView update(@ModelAttribute Memo memo) {
    memoService.update(memo);
    return new ModelAndView("redirect:/memo/read?mno=" + memo.getMno());
  }

  @PostMapping("/memo/delete")
  public ModelAndView delete(@RequestParam Integer mno) {
    memoService.delete(mno);
    return new ModelAndView("redirect:/memo/list");
  }
}
