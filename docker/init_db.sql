CREATE TABLE producto (
    id UUID PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    descripcion VARCHAR(255),
    eliminado_en TIMESTAMP WITH TIME ZONE,

    CONSTRAINT chk_nombre_length CHECK (char_length(nombre) BETWEEN 3 AND 60)
);

CREATE UNIQUE INDEX idx_producto_nombre_ci
    ON producto (LOWER(nombre))
    WHERE eliminado_en IS NULL;

CREATE TYPE estado_version AS ENUM ('ALFA', 'BETA', 'RELEASE');

CREATE TABLE version (
    id UUID PRIMARY KEY,
    producto_id UUID NOT NULL,
    numero_version VARCHAR(20) NOT NULL,
    fecha_lanzamiento DATE NOT NULL DEFAULT CURRENT_DATE,
    estado estado_version NOT NULL,
    notas TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_producto FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE CASCADE,
    
    CONSTRAINT chk_semver CHECK (
        numero_version ~ '^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(-[0-9A-Za-z-]+(\.[0-9A-Za-z-]+)*)?(\+[0-9A-Za-z-]+(\.[0-9A-Za-z-]+)*)?$'
    ),
    
    CONSTRAINT chk_fecha_lanzamiento CHECK (fecha_lanzamiento >= fecha_creacion::date)
);

CREATE INDEX idx_producto_id ON version(producto_id);
CREATE INDEX idx_numero_version ON version(numero_version);