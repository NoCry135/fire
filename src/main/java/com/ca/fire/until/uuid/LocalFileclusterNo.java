package com.ca.fire.until.uuid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 *
 */
public class LocalFileclusterNo implements ClusterNo {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileclusterNo.class);
    private static final String defaultFilePath = "/export/Data/config/clusterNo.txt";
    private String filePath = defaultFilePath;

    public void setFilePath(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return;
        }
        this.filePath = filePath;
    }

    @Override
    public String getClusterNo() {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                String fileContent = FileUtils.readFileToString(file);
                return StringUtils.trimToEmpty(fileContent);
            } catch (IOException e) {
                throw new RuntimeException(MessageFormat.format("read file error!file:{0}", filePath), e);
            }
        }
        return null;
    }

    @Override
    public void saveClusterNo(String value) {
        File file = new File(filePath);
        try {
            FileUtils.write(file, value, false);
        } catch (IOException e) {
            logger.error(MessageFormat.format("save clusterNo error!filePath:{0}", filePath), e);
        }
    }

}
