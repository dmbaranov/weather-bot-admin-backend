package org.weatherbot.admin.member

data class UpdateMemberDto(val name: String?, val deleted: Boolean?, val banned: Boolean?, val moderator: Boolean?)