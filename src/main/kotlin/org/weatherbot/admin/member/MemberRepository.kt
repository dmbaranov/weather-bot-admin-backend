package org.weatherbot.admin.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<ChatMember, ChatMemberId>, JpaSpecificationExecutor<ChatMember>