import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/cmdb/applications',
    method: 'get',
    params: data
  })

}
