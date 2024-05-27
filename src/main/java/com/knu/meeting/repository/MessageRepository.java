package com.knu.meeting.repository;

import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Meeting;

public interface MessageRepository {
    void save(GroupMessage groupMessage);
    void delete(GroupMessage groupMessage);
}
