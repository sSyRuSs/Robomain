CREATE TABLE IF NOT EXISTS iot_device (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    device_code VARCHAR(100) NOT NULL UNIQUE,
    device_name VARCHAR(255) NOT NULL,
    sensor_type VARCHAR(100) NOT NULL,
    unit VARCHAR(32),
    warning_min DOUBLE PRECISION,
    warning_max DOUBLE PRECISION,
    critical_min DOUBLE PRECISION,
    critical_max DOUBLE PRECISION,
    last_value DOUBLE PRECISION,
    last_seen_at TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'OFFLINE',
    auto_create_work_request BOOLEAN NOT NULL DEFAULT TRUE,
    default_maintenance_id UUID,
    asset_id UUID NOT NULL,
    CONSTRAINT fk_iot_device_asset FOREIGN KEY (asset_id) REFERENCES asset(id)
);

CREATE TABLE IF NOT EXISTS iot_telemetry (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    device_id UUID NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    recorded_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_iot_telemetry_device FOREIGN KEY (device_id) REFERENCES iot_device(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS iot_alert (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    device_id UUID NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    threshold DOUBLE PRECISION,
    severity VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    message TEXT NOT NULL,
    triggered_at TIMESTAMP NOT NULL,
    resolved_at TIMESTAMP,
    work_order_id UUID,
    CONSTRAINT fk_iot_alert_device FOREIGN KEY (device_id) REFERENCES iot_device(id) ON DELETE CASCADE,
    CONSTRAINT fk_iot_alert_work_order FOREIGN KEY (work_order_id) REFERENCES work_order(id)
);

CREATE INDEX IF NOT EXISTS idx_iot_device_asset ON iot_device(asset_id);
CREATE INDEX IF NOT EXISTS idx_iot_device_status ON iot_device(status);
CREATE INDEX IF NOT EXISTS idx_iot_telemetry_device_recorded ON iot_telemetry(device_id, recorded_at DESC);
CREATE INDEX IF NOT EXISTS idx_iot_alert_status ON iot_alert(status);
CREATE INDEX IF NOT EXISTS idx_iot_alert_device_status ON iot_alert(device_id, status);
