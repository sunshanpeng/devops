import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/member/members',
    method: 'get',
    params: data
  })
}

export function search(keyword) {
  return request({
    url: '/member/members/search',
    method: 'get',
    params: {"keyword": keyword}
  })
}
