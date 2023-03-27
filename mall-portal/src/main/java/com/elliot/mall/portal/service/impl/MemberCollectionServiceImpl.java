package com.elliot.mall.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elliot.mall.model.UmsMember;
import com.elliot.mall.portal.domain.MemberProductCollection;
import com.elliot.mall.portal.repository.MemberProductCollectionRepository;
import com.elliot.mall.portal.service.MemberCollectionService;
import com.elliot.mall.portal.service.UmsMemberService;

@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {
	@Autowired
	private MemberProductCollectionRepository productCollectionRepository;
	@Autowired
	private UmsMemberService memberService;

	@Override
	public int add(MemberProductCollection productCollection) {
		int count = 0;
		UmsMember member = memberService.getCurrentMember();
		productCollection.setMemberId(member.getId());
		productCollection.setMemberNickname(member.getNickname());
		productCollection.setMemberIcon(member.getIcon());
		MemberProductCollection findCollection = productCollectionRepository
				.findByMemberIdAndProductId(productCollection.getMemberId(), productCollection.getProductId());
		if (findCollection == null) {
			productCollectionRepository.save(productCollection);
			count = 1;
		}
		return count;
	}

	@Override
	public int delete(Long productId) {
		UmsMember member = memberService.getCurrentMember();
		return productCollectionRepository.deleteByMemberIdAndProductId(member.getId(), productId);
	}

	@Override
	public Page<MemberProductCollection> list(Integer pageNum, Integer pageSize) {
		UmsMember member = memberService.getCurrentMember();
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return productCollectionRepository.findByMemberId(member.getId(), pageable);
	}

	@Override
	public MemberProductCollection detail(Long productId) {
		UmsMember member = memberService.getCurrentMember();
		return productCollectionRepository.findByMemberIdAndProductId(member.getId(), productId);
	}

	@Override
	public void clear() {
		UmsMember member = memberService.getCurrentMember();
		productCollectionRepository.deleteAllByMemberId(member.getId());
	}
}
