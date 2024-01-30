package API;

import io.qameta.allure.Feature;

import java.util.ArrayList;
import java.util.Date;

public class VideoData {
    public String getOnDevice() {
        return onDevice;
    }

    public String getOnServer() {
        return onServer;
    }

    public String getType() {
        return type;
    }

    public String getGeometry() {
        return geometry;
    }

    public String getProperties() {
        return properties;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public double getBearing() {
        return bearing;
    }

    public double getVelocity() {
        return velocity;
    }

    public String getUuid() {
        return uuid;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public String getProcessingStatus() {
        return processingStatus;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public Integer getHomographyId() {
        return homographyId;
    }

    public Integer getMergedPartsCount() {
        return mergedPartsCount;
    }

    public Integer getFps() {
        return fps;
    }

    public Integer getSize() {
        return size;
    }

    public Object getResolution() {
        return resolution;
    }

    public Object getErrorReason() {
        return errorReason;
    }

    public Object getErrorModule() {
        return errorModule;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public String getGeoJSON() {
        return geoJSON;
    }

    public VideoData(String onDevice, String onServer, String type, String geometry, String properties, ArrayList<Feature> features, ArrayList<Double> coordinates, Object createdDate, double bearing, double velocity, String uuid, String videoId, String filePath, String sessionId, String deviceId, String descriptions, Integer numbers, String processingStatus, Integer operatorId, Date creationDate, Date processedDate, Integer homographyId, Integer mergedPartsCount, Integer fps, Integer size, Object resolution, Object errorReason, Object errorModule, Object errorMessage, String geoJSON) {
        this.onDevice = onDevice;
        this.onServer = onServer;
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
        this.features = features;
        this.coordinates = coordinates;
        this.createdDate = createdDate;
        this.bearing = bearing;
        this.velocity = velocity;
        this.uuid = uuid;
        this.videoId = videoId;
        this.filePath = filePath;
        this.sessionId = sessionId;
        this.deviceId = deviceId;
        this.descriptions = descriptions;
        this.numbers = numbers;
        this.processingStatus = processingStatus;
        this.operatorId = operatorId;
        this.creationDate = creationDate;
        this.processedDate = processedDate;
        this.homographyId = homographyId;
        this.mergedPartsCount = mergedPartsCount;
        this.fps = fps;
        this.size = size;
        this.resolution = resolution;
        this.errorReason = errorReason;
        this.errorModule = errorModule;
        this.errorMessage = errorMessage;
        this.geoJSON = geoJSON;
    }

    // public class Descriptions{
    public String onDevice;
    public String onServer;
    //   }

    //  public class Feature{
    public String type;
    public String geometry;
    public String properties;
    //  }

    //   public class GeoJSON{
    //       public String type;
    public ArrayList<Feature> features;
    //  }

    //  public class Geometry{
//       public String type;
    public ArrayList<Double> coordinates;
    //   }


    //   public class Properties{
    public Object createdDate;
    public double bearing;
    public double velocity;
    //  }

    //    public class Root{
    public String uuid;
    public String videoId;
    public String filePath;
    public String sessionId;
    public String deviceId;
    public String descriptions;
    public Integer numbers;
    public String processingStatus;
    public Integer operatorId;
    public Date creationDate;
    public Date processedDate;
    public Integer homographyId;
    public Integer mergedPartsCount;
    public Integer fps;
    public Integer size;
    public Object resolution;
    public Object errorReason;
    public Object errorModule;
    public Object errorMessage;
    public String geoJSON;

   }