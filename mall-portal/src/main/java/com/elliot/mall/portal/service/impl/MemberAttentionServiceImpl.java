package com.elliot.mall.portal.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elliot.mall.model.UmsMember;
import com.elliot.mall.portal.domain.MemberBrandAttention;
import com.elliot.mall.portal.repository.MemberBrandAttentionRepository;
import com.elliot.mall.portal.service.MemberAttentionService;
import com.elliot.mall.portal.service.UmsMemberService;

@Service
public class MemberAttentionServiceImpl implements MemberAttentionService {
	@Autowired
	private MemberBrandAttentionRepository memberBrandAttentionRepository;
	@Autowired
	private UmsMemberService memberService;

	@Override
	public int add(MemberBrandAttention memberBrandAttention) {
		int count = 0;
		UmsMember member = memberService.getCurrentMember();
		memberBrandAttention.setMemberId(member.getId());
		memberBrandAttention.setMemberNickname(member.getNickname());
		memberBrandAttention.setMemberIcon(member.getIcon());
		memberBrandAttention.setCreateTime(new Date());
		MemberBrandAttention findAttention = memberBrandAttentionRepository
				.findByMemberIdAndBrandId(memberBrandAttention.getMemberId(), memberBrandAttention.getBrandId());
		if (findAttention == null) {
			memberBrandAttentionRepository.save(memberBrandAttention);
			count = 1;
		}
		return count;
	}

	@Override
	public int delete(Long brandId) {
		UmsMember member = memberService.getCurrentMember();
		return memberBrandAttentionRepository.deleteByMemberIdAndBrandId(member.getId(), brandId);
	}

	@Override
	public Page<MemberBrandAttention> list(Integer pageNum, Integer pageSize) {
		UmsMember member = memberService.getCurrentMember();
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return memberBrandAttentionRepository.findByMemberId(member.getId(), pageable);
	}

	@Override
	public MemberBrandAttention detail(Long brandId) {
		UmsMember member = memberService.getCurrentMember();
		return memberBrandAttentionRepository.findByMemberIdAndBrandId(member.getId(), brandId);
	}

	@Override
	public void clear() {
		UmsMember member = memberService.getCurrentMember();
		memberBrandAttentionRepository.deleteAllByMemberId(member.getId());
	}
}
