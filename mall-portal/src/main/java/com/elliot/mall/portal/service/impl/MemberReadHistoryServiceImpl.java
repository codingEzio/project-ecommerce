package com.elliot.mall.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elliot.mall.model.UmsMember;
import com.elliot.mall.portal.domain.MemberReadHistory;
import com.elliot.mall.portal.repository.MemberReadHistoryRepository;
import com.elliot.mall.portal.service.MemberReadHistoryService;
import com.elliot.mall.portal.service.UmsMemberService;

@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {
	@Autowired
	private MemberReadHistoryRepository memberReadHistoryRepository;
	@Autowired
	private UmsMemberService memberService;

	@Override
	public int create(MemberReadHistory memberReadHistory) {
		UmsMember member = memberService.getCurrentMember();
		memberReadHistory.setMemberId(member.getId());
		memberReadHistory.setMemberNickname(member.getNickname());
		memberReadHistory.setMemberIcon(member.getIcon());
		memberReadHistory.setId(null);
		memberReadHistory.setCreateTime(new Date());
		memberReadHistoryRepository.save(memberReadHistory);
		return 1;
	}

	@Override
	public int delete(List<String> ids) {
		List<MemberReadHistory> deleteList = new ArrayList<>();
		for (String id : ids) {
			MemberReadHistory memberReadHistory = new MemberReadHistory();
			memberReadHistory.setId(id);
			deleteList.add(memberReadHistory);
		}
		memberReadHistoryRepository.deleteAll(deleteList);
		return ids.size();
	}

	@Override
	public Page<MemberReadHistory> list(Integer pageNum, Integer pageSize) {
		UmsMember member = memberService.getCurrentMember();
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(member.getId(), pageable);
	}

	@Override
	public void clear() {
		UmsMember member = memberService.getCurrentMember();
		memberReadHistoryRepository.deleteAllByMemberId(member.getId());
	}
}
