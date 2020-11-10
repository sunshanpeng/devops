import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/member/auth/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/member/members/user',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
