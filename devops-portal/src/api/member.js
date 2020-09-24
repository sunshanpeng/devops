import request from '@/utils/request'

export function list() {
  return request({
    url: '/member/members',
    method: 'get'
  })

}
