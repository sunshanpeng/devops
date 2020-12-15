import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/cmdb/apps',
    method: 'get',
    params: data
  })
}
export function createApp(data) {
  return request({
    url: '/cmdb/apps',
    method: 'post',
    data: data
  })
}
export function dict(params) {
  return request({
    url: `/cmdb/dicts/${params}`,
    method: 'get',
  })
}
export function envClusterTree() {
  return request({
    url: `/cmdb/envs/clusters`,
    method: 'get',
  })
}
