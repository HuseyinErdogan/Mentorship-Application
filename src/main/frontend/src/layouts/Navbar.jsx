import React from 'react'
import { Menu } from 'semantic-ui-react'

export default function Navbar() {
    return (
        <Menu size='massive'>
        <Menu.Item
          name='home'
        />
        <Menu.Item 
          name='Notifications'
        />

      </Menu>
    )
}
