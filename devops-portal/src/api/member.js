import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/member/members',
    method: 'get',
    params: data
  })

}
