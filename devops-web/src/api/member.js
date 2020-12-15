import request from '@/utils/request'

export function memberList(params) {
  return request({
    url: '/member/members',
    method: 'get',
    params: params
  })
}
export function createMember(data) {
  return request({
    url: '/member/members',
    method: 'post',
    data: data
  })
}
export function search(keyword) {
  return request({
    url: '/member/members/search',
    method: 'get',
    params: {"keyword": keyword}
  })
}


export function orgList(params) {
  return request({
    url: '/member/orgs',
    method: 'get',
    params: params
  })
}
export function createOrg(data) {
  return request({
    url: '/member/orgs',
    method: 'post',
    data
  })
}

export function orgTree() {
  return request({
    url: '/member/orgs/tree',
    method: 'get',
  })
}
