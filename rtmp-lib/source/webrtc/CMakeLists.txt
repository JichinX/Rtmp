# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)#版本
set(libfaac source/faac-1.28/libfaac)
set(libfaad2 source/faad2-2.7/libfaad)
set(libanycore source/AnyCore)
set(libwebrtc   source/webrtc )
set(libyuv   source/libyuv/source )
include_directories(
		${libanycore}/srs_librtmp
		${libfaac}/../include
		${libfaad2}/../include
        ${libwebrtc}/common_video/include
        ${libwebrtc}/common_video/libyuv/include
        ${libyuv}/../include
        ${libfaad2}/../libfaad
		)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp )
#add_library( anyrtmp-jni SHARED source/ )
add_library( faac
    SHARED
    ${libfaac}/aacquant.c
 	${libfaac}/bitstream.c
 	${libfaac}/fft.c
 	${libfaac}/frame.c
 	${libfaac}/midside.c
 	${libfaac}/psychkni.c
 	${libfaac}/util.c
 	${libfaac}/backpred.c
 	${libfaac}/channels.c
 	${libfaac}/filtbank.c
 	${libfaac}/huffman.c
 	${libfaac}/ltp.c
 	${libfaac}/tns.c
 	)
add_library( faad2 
    SHARED
    ${libfaad2}/bits.c
    ${libfaad2}/cfft.c
    ${libfaad2}/decoder.c
    ${libfaad2}/drc.c
    ${libfaad2}/drm_dec.c
    ${libfaad2}/error.c
    ${libfaad2}/filtbank.c
    ${libfaad2}/ic_predict.c
    ${libfaad2}/is.c
    ${libfaad2}/lt_predict.c
    ${libfaad2}/mdct.c
    ${libfaad2}/mp4.c
    ${libfaad2}/ms.c
    ${libfaad2}/output.c
    ${libfaad2}/pns.c
    ${libfaad2}/ps_dec.c
    ${libfaad2}/ps_syntax.c
    ${libfaad2}/pulse.c
    ${libfaad2}/specrec.c
    ${libfaad2}/syntax.c
    ${libfaad2}/tns.c
    ${libfaad2}/hcr.c
    ${libfaad2}/huffman.c
    ${libfaad2}/rvlc.c
    ${libfaad2}/ssr.c
    ${libfaad2}/ssr_fb.c
    ${libfaad2}/ssr_ipqf.c
    ${libfaad2}/common.c
    ${libfaad2}/sbr_dct.c
    ${libfaad2}/sbr_e_nf.c
    ${libfaad2}/sbr_fbt.c
    ${libfaad2}/sbr_hfadj.c
    ${libfaad2}/sbr_hfgen.c
    ${libfaad2}/sbr_huff.c
    ${libfaad2}/sbr_qmf.c
    ${libfaad2}/sbr_syntax.c
    ${libfaad2}/sbr_tf_grid.c
    ${libfaad2}/sbr_dec.c
    )
#$(libanycore)/srs_librtmp/srs_librtmp.cpp
add_library( anycore
    STATIC
    $(libanycore)/aacencode.cc
    $(libanycore)/aacdecode.cc 
    $(libanycore)/anyrtmpcore.cc 
    $(libanycore)/anyrtmplayer.cc 
    $(libanycore)/anyrtmpstreamer.cc 
    $(libanycore)/anyrtmpull.cc 
    $(libanycore)/anyrtmpush.cc 
    $(libanycore)/avcodec.cc 
    $(libanycore)/plybuffer.cc 
    $(libanycore)/plydecoder.cc 
    $(libanycore)/RtmpGuesterImpl.cc 
    $(libanycore)/RtmpHosterImpl.cc 
    $(libanycore)/videofilter.cc
   )
add_library( webrtc
    STATIC
	${libwebrtc}/common_types.cc
    ${libwebrtc}/api/androidvideocapturer.cc
    ${libwebrtc}/base/asyncinvoker.cc
    ${libwebrtc}/base/asyncfile.cc
    ${libwebrtc}/base/asyncresolverinterface.cc
    ${libwebrtc}/base/asyncsocket.cc
    ${libwebrtc}/base/asyncpacketsocket.cc
    ${libwebrtc}/base/asynctcpsocket.cc
    ${libwebrtc}/base/asyncudpsocket.cc
    ${libwebrtc}/base/base64.cc
    ${libwebrtc}/base/bitbuffer.cc
    ${libwebrtc}/base/bytebuffer.cc
    ${libwebrtc}/base/checks.cc
    ${libwebrtc}/base/criticalsection.cc
    ${libwebrtc}/base/event.cc
    ${libwebrtc}/base/event_tracer.cc
    ${libwebrtc}/base/ifaddrs-android.cc
    ${libwebrtc}/base/ipaddress.cc
    ${libwebrtc}/base/logging.cc
    ${libwebrtc}/base/location.cc
    ${libwebrtc}/base/messagehandler.cc
    ${libwebrtc}/base/messagequeue.cc
    ${libwebrtc}/base/nullsocketserver.cc
    ${libwebrtc}/base/nethelpers.cc
    ${libwebrtc}/base/physicalsocketserver.cc
    ${libwebrtc}/base/platform_thread.cc
    ${libwebrtc}/base/sharedexclusivelock.cc
    ${libwebrtc}/base/signalthread.cc
    ${libwebrtc}/base/sigslot.cc
    ${libwebrtc}/base/socketaddress.cc
    ${libwebrtc}/base/stringencode.cc
    ${libwebrtc}/base/thread.cc
    ${libwebrtc}/base/timeutils.cc
    ${libwebrtc}/base/timing.cc
    ${libwebrtc}/base/timestampaligner.cc

    ${libwebrtc}/common_audio/resampler/push_resampler.cc
    ${libwebrtc}/common_audio/resampler/push_sinc_resampler.cc
    ${libwebrtc}/common_audio/resampler/resampler.cc
    ${libwebrtc}/common_audio/resampler/sinc_resampler.cc
    ${libwebrtc}/common_audio/signal_processing/spl_init.c
    ${libwebrtc}/common_audio/signal_processing/cross_correlation.c
    ${libwebrtc}/common_audio/signal_processing/downsample_fast.c
    ${libwebrtc}/common_audio/signal_processing/min_max_operations.c
    ${libwebrtc}/common_audio/signal_processing/vector_scaling_operations.c
    ${libwebrtc}/common_audio/audio_util.cc
    ${libwebrtc}/common_audio/ring_buffer.c
    ${libwebrtc}/common_audio/resampler/sinc_resampler_sse.cc
    ${libwebrtc}/common_video/h264/h264_common.cc
    ${libwebrtc}/common_video/h264/pps_parser.cc
    ${libwebrtc}/common_video/h264/sps_parser.cc
    ${libwebrtc}/common_video/h264/sps_vui_rewriter.cc
    ${libwebrtc}/common_video/libyuv/webrtc_libyuv.cc
    ${libwebrtc}/common_video/i420_buffer_pool.cc
    ${libwebrtc}/common_video/video_frame.cc
    ${libwebrtc}/common_video/incoming_video_stream.cc
    ${libwebrtc}/common_video/video_frame_buffer.cc
    ${libwebrtc}/common_video/video_render_frames.cc

    ${libwebrtc}/media/base/mediaconstants.cc
    ${libwebrtc}/media/base/videoadapter.cc
    ${libwebrtc}/media/base/videobroadcaster.cc
    ${libwebrtc}/media/base/videocapturer.cc
    ${libwebrtc}/media/base/videocommon.cc
    ${libwebrtc}/media/base/videoframe.cc
    ${libwebrtc}/media/base/videoframefactory.cc
    ${libwebrtc}/media/base/videosourcebase.cc
    ${libwebrtc}/media/engine/webrtcvideoframe.cc
    ${libwebrtc}/media/engine/webrtcvideoframefactory.cc

    ${libwebrtc}/modules/audio_coding/acm2/acm_resampler.cc
    ${libwebrtc}/modules/audio_device/audio_device_impl.cc
    ${libwebrtc}/modules/audio_device/audio_device_buffer.cc
    ${libwebrtc}/modules/audio_device/audio_device_generic.cc
    ${libwebrtc}/modules/audio_device/android/audio_manager.cc
    ${libwebrtc}/modules/audio_device/android/audio_record_jni.cc
    ${libwebrtc}/modules/audio_device/android/audio_track_jni.cc
    ${libwebrtc}/modules/audio_device/android/build_info.cc
    ${libwebrtc}/modules/audio_device/android/opensles_common.cc
    ${libwebrtc}/modules/audio_device/android/opensles_player.cc
    ${libwebrtc}/modules/audio_device/dummy/audio_device_dummy.cc
    ${libwebrtc}/modules/audio_device/fine_audio_buffer.cc
    ${libwebrtc}/modules/utility/source/helpers_android.cc
    ${libwebrtc}/modules/utility/source/jvm_android.cc
    ${libwebrtc}/modules/video_coding/codecs/h264/h264.cc
    ${libwebrtc}/modules/video_coding/codecs/h264/h264_encoder_impl.cc
    ${libwebrtc}/modules/video_coding/codecs/h264/h264_decoder_impl.cc
    ${libwebrtc}/modules/video_coding/utility/h264_bitstream_parser.cc
    ${libwebrtc}/modules/video_coding/utility/quality_scaler.cc

    ${libwebrtc}/system_wrappers/source/aligned_malloc.cc
    ${libwebrtc}/system_wrappers/source/atomic32_non_darwin_unix.cc
    ${libwebrtc}/system_wrappers/source/clock.cc
    ${libwebrtc}/system_wrappers/source/cpu_info.cc
    ${libwebrtc}/system_wrappers/source/cpu_features.cc
    ${libwebrtc}/system_wrappers/source/data_log_c.cc
    ${libwebrtc}/system_wrappers/source/event.cc
    ${libwebrtc}/system_wrappers/source/event_timer_posix.cc
    ${libwebrtc}/system_wrappers/source/file_impl.cc
    ${libwebrtc}/system_wrappers/source/logging.cc
    ${libwebrtc}/system_wrappers/source/rtp_to_ntp.cc
    ${libwebrtc}/system_wrappers/source/rw_lock.cc
    ${libwebrtc}/system_wrappers/source/rw_lock_posix.cc
    ${libwebrtc}/system_wrappers/source/sleep.cc
    ${libwebrtc}/system_wrappers/source/sort.cc
    ${libwebrtc}/system_wrappers/source/timestamp_extrapolator.cc
    ${libwebrtc}/system_wrappers/source/trace_impl.cc
    ${libwebrtc}/system_wrappers/source/trace_impl.h
    ${libwebrtc}/system_wrappers/source/trace_posix.cc
    ${libwebrtc}/system_wrappers/source/field_trial_default.cc
    ${libwebrtc}/system_wrappers/source/metrics_default.cc
)
add_library( yuv_static  STATIC
    ${libyuv}/compare.cc
    ${libyuv}/compare_common.cc
    ${libyuv}/compare_neon64.cc
    ${libyuv}/compare_gcc.cc
    ${libyuv}/convert.cc
    ${libyuv}/convert_argb.cc
    ${libyuv}/convert_from.cc
    ${libyuv}/convert_from_argb.cc
    ${libyuv}/convert_to_argb.cc
    ${libyuv}/convert_to_i420.cc
    ${libyuv}/cpu_id.cc
    ${libyuv}/planar_functions.cc
    ${libyuv}/rotate.cc
    ${libyuv}/rotate_any.cc
    ${libyuv}/rotate_argb.cc
    ${libyuv}/rotate_common.cc
    ${libyuv}/rotate_mips.cc
    ${libyuv}/rotate_neon64.cc
    ${libyuv}/rotate_gcc.cc
    ${libyuv}/row_any.cc
    ${libyuv}/row_common.cc
    ${libyuv}/row_mips.cc
    ${libyuv}/row_neon64.cc
    ${libyuv}/row_gcc.cc
    ${libyuv}/scale.cc
    ${libyuv}/scale_any.cc
    ${libyuv}/scale_argb.cc
    ${libyuv}/scale_common.cc
    ${libyuv}/scale_mips.cc
    ${libyuv}/scale_neon64.cc
    ${libyuv}/scale_gcc.cc
    ${libyuv}/video_common.cc
    )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )