import tensorflow as tf
from tensorflow.keras import datasets, layers, models
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Dense , Flatten
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import tensorflow as tf
import keras
from tensorflow.keras import datasets,layers,models
import cv2
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import load_model
import os
import pandas as pd
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.callbacks import EarlyStopping, ReduceLROnPlateau
from tensorflow.keras.applications import MobileNetV2
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dense, GlobalAveragePooling2D, Dropout
from tensorflow.keras.optimizers import Adam
from sklearn.model_selection import train_test_split
train_data_dir='images'
test_dir='test'
train_datagen = ImageDataGenerator(rescale=1./255, validation_split=0.2)  
target_size = (224, 224)
batch_size = 32

train_generator = train_datagen.flow_from_directory(
    train_data_dir,
    target_size=target_size,
    batch_size=batch_size,
    class_mode='categorical',
    subset='training' 
)

validation_generator = train_datagen.flow_from_directory(
    train_data_dir,
    target_size=target_size,
    batch_size=batch_size,
    class_mode='categorical',
    subset='validation'  
)


early_stopping = EarlyStopping(monitor='val_loss', patience=10, restore_best_weights=True)
reduce_lr = ReduceLROnPlateau(monitor='val_loss', factor=0.2, patience=3, min_lr=1e-6)

base_model = MobileNetV2(weights='imagenet', include_top=False, input_shape=(224, 224, 3))

for layer in base_model.layers:
    layer.trainable = False

x = GlobalAveragePooling2D()(base_model.output)
x = Dense(1024, activation='relu')(x)
x = Dropout(0.5)(x)
predictions = Dense(4, activation='softmax')(x)

model = Model(inputs=base_model.input, outputs=predictions)

model.compile(optimizer=Adam(learning_rate=0.01),
              loss='categorical_crossentropy',
              metrics=['accuracy'])

history = model.fit(
    train_generator,
    epochs=20,
    validation_data=validation_generator,
    callbacks=[early_stopping,reduce_lr]
)
test_datagen = ImageDataGenerator(
    rescale=1./255,
)
target_size = (224, 224)
batch_size = 32
testing_dir = test_dir
test_generator = test_datagen.flow_from_directory(
    testing_dir,
    target_size=target_size,
    batch_size=batch_size,
    class_mode=None,  
    shuffle=False  
)
test_predictions = model.predict(test_generator)
filenames = [os.path.splitext(os.path.basename(filename))[0] for filename in test_generator.filenames]
predicted_class_indices = test_predictions.argmax(axis=-1)
class_indices = train_generator.class_indices
class_names = {v: k for k, v in class_indices.items()}
predicted_classes = [class_names[idx] for idx in predicted_class_indices]
result_df = pd.DataFrame({'id': filenames, 'Prediction': predicted_classes})
result_df.to_csv('submission.csv', index=False)