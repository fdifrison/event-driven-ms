package com.fdifrison.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_view")
class UserView extends BaseUser<UserView> {

}
