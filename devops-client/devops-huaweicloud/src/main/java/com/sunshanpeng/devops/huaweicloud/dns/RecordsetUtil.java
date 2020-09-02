package com.sunshanpeng.devops.huaweicloud.dns;

import com.huawei.openstack4j.model.dns.v2.Recordset;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.sunshanpeng.devops.common.exception.BusinessException;
import com.sunshanpeng.devops.common.util.JsonUtil;
import com.sunshanpeng.devops.common.util.UrlUtil;
import com.sunshanpeng.devops.huaweicloud.HuaweicloudClient;
import com.sunshanpeng.devops.resource.dto.DomainRecordDTO;
import com.sunshanpeng.devops.resource.enums.RecordTypeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RecordsetUtil {

    private HuaweicloudClient huaweicloudClient;
    private static final Map<String, String> zoneMap = new ConcurrentHashMap<>();

    public RecordsetUtil(HuaweicloudClient huaweicloudClient) {
        this.huaweicloudClient = huaweicloudClient;
    }

    public List<? extends Recordset> list(@NotNull DomainRecordDTO domainRecord) {
        String zoneId = zoneId(domainRecord.getDomainName());
        return huaweicloudClient.getClient().dns().recordsets().list(zoneId);
    }

    public void add(String domain, String value) {
        DomainRecordDTO domainRecord = DomainRecordDTO.builder().rr(UrlUtil.getRR(domain)).domainName(UrlUtil.getHost(domain))
                .recordType(RecordTypeEnum.A).value(value).build();
        add(domainRecord);
    }

    public void add(DomainRecordDTO domainRecord) {
        String zoneId = zoneId(domainRecord.getDomainName());
        List<String> records = new ArrayList<>();
        records.add(domainRecord.getValue());
        huaweicloudClient.getClient().dns().recordsets().create(zoneId, domainRecord.getFullDomain(), domainRecord.getDescription(),
                        domainRecord.getRecordType().getValue(),domainRecord.getTtl(), records);
    }

    private String zoneId(@NotEmpty String zoneName) {
        if (zoneMap.containsKey(zoneName)) {
            return zoneMap.get(zoneName);
        }
        List<? extends Zone> zoneList = huaweicloudClient.getClient().dns().zones().list(ZoneType.PRIVATE, null, null);
        Optional<String> zoneId = zoneList.stream().filter(zone -> ((zoneName + ".").equals(zone.getName())))
                .findAny().map(Zone::getId);
        if (!zoneId.isPresent()) {
            log.error("zoneList:{}", JsonUtil.toJSONString(zoneList));
            throw new BusinessException(String.format("获取zoneId异常: %s", zoneName));
        }
        zoneMap.put(zoneName, zoneId.get());
        return zoneId.get();
    }
}
