package core.tinyos;

import model.NavigationNotification;

/**
 * Created by ahmet on 4/11/17.
 */

public class NavigationMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 8;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 147;

    /** Create a new OscilloscopeMsg of size 28. */
    public NavigationMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new OscilloscopeMsg of the given data_length. */
    public NavigationMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new OscilloscopeMsg with the given data_length
     * and base offset.
     */
    public NavigationMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new OscilloscopeMsg using the given byte array
     * as backing store.
     */
    public NavigationMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new OscilloscopeMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public NavigationMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new OscilloscopeMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public NavigationMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new OscilloscopeMsg embedded in the given message
     * at the given base offset.
     */
    public NavigationMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new OscilloscopeMsg embedded in the given message
     * at the given base offset and length.
     */
    public NavigationMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
        String s = "Message <OscilloscopeMsg> \n";
        try {
            s += "  [id=0x"+Long.toHexString(get_id())+"]\n";
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
        try {
            s += "  [child=0x"+Long.toHexString(get_parent_id())+"]\n";
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
        try {
            s += "  [count=0x"+Long.toHexString(get_count())+"]\n";
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
        try {
            s += "  [mobileMoteId=0x"+Long.toHexString(get_mobile_moteId())+"]\n";
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
        return s;
    }

    /**
     /* Return a Ligintensitry representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public NavigationNotification toNavigationNotification() {
        NavigationNotification response = new NavigationNotification();
        try {
            response.setId(get_id());
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }

        try {
            response.setParent(get_parent_id());
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }

        try {
            response.setMobileId(get_mobile_moteId());
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }

        try {
            response.setCount(get_count());
        } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
        response.setCreatedAt(System.currentTimeMillis());
        return response;
    }

    /**
     * Return the offset (in bits) of the field 'id'
     */
    public static int offsetBits_id() {
        return 0;
    }

    /**
     * Return the offset (in bits) of the field 'id'
     */
    public static int offsetBits_parentId() {
        return 3*16;
    }

    /**
     * Return the offset (in bits) of the field 'id'
     */
    public static int offsetBits_mobile_moteId() {
        return 2*16;
    }


    /**
     * Return the value (as a int) of the field 'id'
     */
    public int get_id() {
        return (int)getUIntBEElement(offsetBits_id(), 16);
    }

    /**
     * Return the value (as a int) of the field 'id'
     */
    public int get_parent_id() {
        return (int)getUIntBEElement(offsetBits_parentId(), 16);
    }


    /**
     * Return the value (as a int) of the field 'id'
     */
    public int get_mobile_moteId() {
        return (int)getUIntBEElement(offsetBits_mobile_moteId(), 16);
    }


    /**
     * Set the value of the field 'id'
     */
    public void set_id(int value) {
        setUIntBEElement(offsetBits_id(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'id'
     */
    public static int size_id() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'id'
     */
    public static int sizeBits_id() {
        return 16;
    }


    /**
     * Return the offset (in bytes) of the field 'count'
     */
    public static int offset_count() {
        return (48 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'count'
     */
    public static int offsetBits_count() {
        return 1*16;
    }

    /**
     * Return the value (as a int) of the field 'count'
     */
    public int get_count() {
        return (int)getUIntBEElement(offsetBits_count(), 16);
    }

    /**
     * Set the value of the field 'count'
     */
    public void set_count(int value) {
        setUIntBEElement(offsetBits_count(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'count'
     */
    public static int size_count() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'count'
     */
    public static int sizeBits_count() {
        return 16;
    }



}