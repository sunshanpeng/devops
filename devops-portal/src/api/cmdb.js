import request from '@/utils/request'

export function list() {
  return request({
    url: '/cmdb/applications',
    method: 'get'
  })

}
